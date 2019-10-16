# -*- coding:utf-8 -*-
from sys import argv
from exercises import exercise

inputLen = len(argv)
#如果需要生成题目
if (inputLen==5) and ('-r' in argv) and ('-n' in argv):
    #获取题目的数量和范围
    for i in range(inputLen):
        if argv[i] == '-r':
            userRange = int(argv[i+1])
        elif argv[i] == '-n':
            userAccount = int(argv[i+1])
        else:
            continue
    mathExercise = exercise(userAccount,userRange)
    mathExercise.makeExe()
    print("小学计算题已生成完成！请在文件user.txt中按格式完成！")
    print("我和我的祖国，一刻也不能分离！")
#如果需要对答案
elif (inputLen==5) and (argv[1] == '-e') and (argv[3] == '-a'):
    mathExercise = exercise()
    exerciseFile = argv[2]  #获取题目文件
    answerFile = argv[4]    #获取用户需要核对的文件
    mathExercise.confirmExe(exerciseFile,answerFile)
    print("同学，您的答题情况已存入Grade.txt中")
else:
    print("输入错误！正确的输入格式：Myapp.exe -n *** -r ***(n代表题目数量、r代表数字范围）")

