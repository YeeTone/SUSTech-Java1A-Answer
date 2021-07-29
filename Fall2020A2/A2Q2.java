package Fall2020A2;

import java.sql.Statement;
import java.util.*;

public class A2Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PositionNode[][] graphNode = new PositionNode[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            char[] line;
            if(i==0){
                line = null;
            }else {
                line = sc.next().toCharArray();
            }

            for (int j = 0; j <= m; j++) {
                graphNode[i][j]= new PositionNode(i,j);
                if(i==0||j==0){
                    continue;
                }

                graphNode[i][j].isWall = (line[j-1]=='W');
            }
        }

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                PositionNode pn = graphNode[i][j];
                if(pn.isWall){
                    continue;
                }
                for (int[] dir : directions) {
                    if(i + dir[0]<0 || i + dir[0]>n || j + dir[1]<0 || j + dir[1]>m){
                        continue;
                    }

                    PositionNode neighbor = graphNode[i + dir[0]][j + dir[1]];
                    if (neighbor.isWall) {
                        continue;
                    }

                    pn.neighbors.add(neighbor);
                    neighbor.neighbors.add(pn);
                }
            }
        }

        graphNode[1][1].bfs();
        printAnswer(graphNode[n][m]);
    }
    private static void printAnswer(PositionNode node){
        if(node.father==null){
            System.out.println("No");
            return;
        }

        System.out.println("Yes");
        Stack<PositionNode> nodeStack = new Stack<>();
        PositionNode temp = node;
        while (temp!=null){
            nodeStack.push(temp);
            temp = temp.father;
        }

        while (!nodeStack.isEmpty()){
            System.out.println(nodeStack.pop());
        }
    }

    private static class PositionNode {
        boolean isWall;
        boolean confirmed;
        int distanceFrom11;
        int x;
        int y;
        PositionNode father;
        Set<PositionNode> neighbors;

        public PositionNode(int x, int y){
            this.isWall = true;
            this.confirmed = false;
            this.distanceFrom11 = Integer.MAX_VALUE - 125;

            this.x=x;
            this.y=y;

            this.father = null;
            this.neighbors = new HashSet<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PositionNode that = (PositionNode) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public void bfs(){
            this.confirmed = true;
            this.distanceFrom11 = 0;

            Queue<PositionNode> pnQueue= new LinkedList<>();
            pnQueue.offer(this);

            while (!pnQueue.isEmpty()){
                PositionNode poll = pnQueue.poll();
                poll.confirmed = true;

                for (PositionNode nei:poll.neighbors){
                    if(nei.confirmed){
                        continue;
                    }

                    pnQueue.offer(nei);
                    if(nei.distanceFrom11>poll.distanceFrom11+1){
                        nei.distanceFrom11 = poll.distanceFrom11 +1;
                        nei.father = poll;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "("+this.x+","+this.y+")";
        }
    }
}
