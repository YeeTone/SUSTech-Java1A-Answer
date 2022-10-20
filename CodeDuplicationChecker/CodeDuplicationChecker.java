import jplag.ExitException;
import jplag.Program;
import jplag.options.CommandLineOptions;
import jplag.options.Options;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class CodeDuplicationChecker {
    private static final String RUBBISH = "\uFEFF";
    private static final Map<String, String> studentId_studentGroup = new TreeMap<>(Comparator.naturalOrder());
    private static final Set<String> QUESTIONS = new HashSet<>();

    private static void prepareDirectories() throws IOException {
        ensureDirectory("./reformattedCode");
        ensureDirectory("./result");
    }

    private static void ensureDirectory(String dir) throws IOException {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }

        if (dirFile.exists() && !dirFile.isDirectory()) {
            Files.delete(dirFile.toPath());
            dirFile.mkdir();
        }

        // reference: https://blog.csdn.net/qq_33965490/article/details/123558096

        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //遍历文件时删除文件
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dire, IOException exc) throws IOException {
                //遍历目录时删除目录
                if (!dire.equals(Paths.get(dir))) {
                    Files.delete(dire);
                }

                return super.postVisitDirectory(dire, exc);
            }
        });
    }

    private static void prepareStudentGroup(String... csvFiles) {
        for (String csv : csvFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split(",");
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].startsWith(RUBBISH)) {
                            split[i] = split[i].substring(1);
                        }
                    }
                    studentId_studentGroup.put(split[0], split[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String... args) throws IOException {
        assert args[0].endsWith("/final_submissions");
        prepareDirectories();
        prepareStudentGroup(Arrays.stream(args).skip(1).toArray(String[]::new));
        collectRewrite(args[0]);
        runJPlag();
    }

    private static void collectRewrite(String finalSubmissionPath) throws IOException {
        File f = new File(finalSubmissionPath);

        assert f.exists();
        assert f.isDirectory();
        Files.walk(Path.of(f.getAbsolutePath())).filter(e -> {
            File file = new File(String.valueOf(e));
            return !file.isDirectory() && !file.getName().endsWith(".JCoder");
        }).forEach(path -> {
            File sourceFile = new File(String.valueOf(path));
            System.out.println(sourceFile);
            String fileName = sourceFile.getName();
            String studentId = sourceFile.getParentFile().getName();
            String questionId = sourceFile.getParentFile().getParentFile().getName();
            System.out.println(sourceFile.getParentFile().getParentFile().getParentFile().toPath().normalize());
            String groupId = sourceFile.getParentFile().getParentFile().getParentFile().getName().substring(0, 5) +
                    studentId_studentGroup.get(studentId.substring(1));

            String finalName = String.join("_", groupId, questionId, studentId, fileName);

            String output = "./reformattedCode";
            File questionDirectory = new File(output + "/" + questionId);
            if (!questionDirectory.exists()) {
                questionDirectory.mkdir();
            }
            QUESTIONS.add(questionId);
            File destFile = new File(questionDirectory.getAbsolutePath() + "/" + finalName);
            copyFile(sourceFile, destFile);
        });
    }

    private static void copyFile(File src, File dst) {
        try (FileChannel in = new FileInputStream(src).getChannel();
             FileChannel out = new FileOutputStream(dst).getChannel()) {
            out.transferFrom(in, 0, in.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runJPlag() {
        QUESTIONS.forEach(e -> {
            String f = String.valueOf(Path.of(".", "reformattedCode", e));
            Options o;
            Program p = null;
            try {
                o = new CommandLineOptions(new String[]{f}, null);
                p = new Program(o);
                o.result_dir += File.separator + e;
                p.run();
            } catch (ExitException ex) {
                ex.printStackTrace();
            } finally {
                if (p != null) {
                    p.closeWriter();
                }
            }
        });
    }
}
