import csv
import time

import pandas


def prn_obj(obj):
    print(','.join(['%s:%s' % item for item in obj.__dict__.items()]))


class student(object):
    def __init__(self, length):
        self.class_number = None
        self.name = None
        self.id = None
        self.score = [0 for _ in range(0, length)]


answer_CSV = pandas.read_csv('expectedAnswer.csv', encoding='gbk')
useless = {'编号', '开始答题时间', '结束答题时间', '答题时长', '地理位置国家和地区', '地理位置省', '地理位置市', '自定义字段'}

answer = dict()

for i in range(0, len(answer_CSV.columns)):
    col = answer_CSV.columns[i]
    if col in useless:
        continue
    answer[col] = str(answer_CSV.values[0][i]).strip()

print(answer)

students_dict = {}

personal = ['class', ' name', 'ID']

student_answer_csv = pandas.read_csv('9799234_202203072024068846.csv')

for i in range(0, len(student_answer_csv.values), 1):
    s = student(length=4)
    index = 0
    for j in range(0, len(student_answer_csv.columns), 1):
        col = student_answer_csv.columns[j]

        if col in useless:
            continue
        elif personal[0] in col:
            s.class_number = str(student_answer_csv.values[i][j]).replace('<br />', '').strip()
        elif personal[1] in col:
            s.name = str(student_answer_csv.values[i][j]).replace('<br />\n', '').strip()
        elif personal[2] in col:
            s.id = str(student_answer_csv.values[i][j]).replace('<br />\n', '').replace('\t', '').strip()
        else:
            if answer[col] == str(student_answer_csv.values[i][j]).replace('<br />\n', '').strip() \
                    and answer[col] != 'nan':
                s.score[index] = 1
            index += 1

    if s.id not in students_dict.keys():
        students_dict[s.id] = s

peopleCSV = pandas.read_csv('people6.csv', encoding='gbk')
id = peopleCSV['学号']
name = peopleCSV['姓名']

with open('quiz1-6.csv', 'w', newline='') as csvfile:
    sw = csv.writer(csvfile, dialect='excel')

    sw.writerow(['学号', '姓名', 'Quiz 1成绩'])
    for i in range(0, len(id)):
        if str(id[i]) in students_dict.keys():
            sw.writerow([id[i], name[i], sum(students_dict[str(id[i])].score)])
        else:
            sw.writerow([id[i], name[i], 0])
