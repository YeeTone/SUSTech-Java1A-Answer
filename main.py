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

personal = ['class', ' name', 'ID']

student_answer_csv = pandas.read_csv('9799234_202203072020311613.csv')

for i in range(0, len(student_answer_csv.values), 1):
    s = student(length=4)
    index = 0
    for j in range(0, len(student_answer_csv.columns), 1):
        col = student_answer_csv.columns[j]

        if col in useless:
            continue
        elif personal[0] in col:
            s.class_number = str(student_answer_csv.values[i][j]).replace('<br />', '')
        elif personal[1] in col:
            s.name = str(student_answer_csv.values[i][j]).replace('<br />\n', '')
        elif personal[2] in col:
            s.id = str(student_answer_csv.values[i][j]).replace('<br />\n', '').replace('\t', '')
        else:
            if answer[col] == str(student_answer_csv.values[i][j]).replace('<br />\n', '').strip() \
                    and answer[col] != 'nan':
                s.score[index] = 1
            index += 1

    prn_obj(s)
