# -*- coding: utf-8 -*-
import time
from MasterProblem_GB1 import MasterProblem_GB1
import matplotlib.pyplot as plt
# import csv
import pandas as pd
import xlrd,xlwt

path="C:/Users/liuzx/Desktop/智能调度决策——自动化所/自动化所——调度/代码新/数据导入3.xlsx"
wb = xlrd.open_workbook(path)
# 获取参数赋值表
sheet1 = wb.sheet_by_name("参数赋值表")
# 总行数
nrows = sheet1.nrows
# 总列数
ncols = sheet1.ncols
print(nrows,ncols)
# 取第一行的参数
line_value=sheet1.row_values(0)
print(line_value)


def getColumnIndex(table, columnName):
    columnIndex = None
     #print table
    for i in range(table.ncols):
        if(table.cell_value(0, i) == columnName):
            columnIndex = i
            break
    return columnIndex


# excel输入
def create_data_model():
    """Stores the data for the problem."""
    data = {}
    data['period'] = int(sheet1.cell_value(1, getColumnIndex(sheet1,'调度周期')))
    counttype_technician=3
    data['technician']=[]
    for i in range(1,1+counttype_technician):
        data['technician'].append(int(sheet1.cell_value(i, getColumnIndex(sheet1,'技工日工资'))))
    data['base'] = {}
    count_base=1  # 码头个数
    data['base']['coordinate']=[]
    for i in range(1,1+count_base):
        base_x=sheet1.cell_value(i, getColumnIndex(sheet1,'码头坐标X'))
        base_y=sheet1.cell_value(i, getColumnIndex(sheet1,'码头坐标Y'))
        data['base']['coordinate'].append((base_x,base_y))

    data['base']['technician']=[]
    for b in range(0,count_base):
        data['base']['technician'].append([])
        for j in range(counttype_technician):
            data['base']['technician'][b].append([])
            for i in range(data['period']):
                data['base']['technician'][b][j].append(int(sheet1.cell_value(i+1, getColumnIndex(sheet1,'%d类技工总人数'% (j+1)))))

    data['wind_farm'] = {}
    count_wind_farm=2  #需要维修的风电场个数
    count_wind_turbine=[8,8] #每个风电场需要维修的风机个数
    count_wind_turbine_sum=[36,36]# 每个风电场所有的风机个数
    data['wind_farm']['maintenance_time']=[]
    count_wturbine=[] #用于计数,记录不同风电场风机信息在Excel位置
    count_wturbine_l=0
    for i in range(count_wind_farm):
        count_wturbine.append(count_wturbine_l)
        count_wturbine_l=count_wturbine_l+count_wind_turbine[i]
    count_turbine=[]
    count_turbine_l=0
    for i in range(count_wind_farm):
        count_turbine.append(count_turbine_l)
        count_turbine_l=count_turbine_l+count_wind_turbine_sum[i]

    ###设定与风电场相关的参数
    for i in range(count_wind_farm):
        data['wind_farm']['maintenance_time'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['maintenance_time'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'风机维护时间'))))

    data['wind_farm']['technician']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['technician'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['technician'][i].append([])
            for k in range(counttype_technician):
                data['wind_farm']['technician'][i][j].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'%d类技工需求量'% (k+1)))))


    data['wind_farm']['parts_weight']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['parts_weight'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['parts_weight'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'风机所需备件重量'))))

    data['wind_farm']['present']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['present'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['present'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'风机在维修时是否需要船停泊'))))

    data['wind_farm']['deadline']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['deadline'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['deadline'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'最晚建议维修时间'))))

    data['wind_farm']['penalty_cost']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['penalty_cost'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['penalty_cost'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'逾时惩罚成本'))))

    data['vessel'] = {}
    counttype_vessel=3
    data['vessel']['capacity']=[]
    for i in range(counttype_vessel):
        data['vessel']['capacity'].append(int(sheet1.cell_value(i+1, getColumnIndex(sheet1,'船的备件容量'))))

    data['vessel']['technician']=[]
    for i in range(counttype_vessel):
        data['vessel']['technician'].append(int(sheet1.cell_value(i+1, getColumnIndex(sheet1,'船的人员可载量'))))

    data['vessel']['cost']=[]
    for i in range(counttype_vessel):
        data['vessel']['cost'].append(int(sheet1.cell_value(i+1, getColumnIndex(sheet1,'船的油费'))))

    data['vessel']['speed']=[]
    for i in range(counttype_vessel):
        data['vessel']['speed'].append(int(sheet1.cell_value(i+1, getColumnIndex(sheet1,'船的航速'))))

    data['vessel']['trans_time']=[] # 这里默认转移时间跟船的类型没有关系，与时期有关
    for i in range(data['period']):
        data['vessel']['trans_time'].append(sheet1.cell_value(i+1, getColumnIndex(sheet1,'技工转移时间')))

    data['vessel']['time_window']=[]
    for i in range(counttype_vessel):
        data['vessel']['time_window'].append([])
        for j in range(data['period']):
            data['vessel']['time_window'][i].append([])
            for k in range(count_wind_farm):
                data['vessel']['time_window'][i][j].append(int(sheet1.cell_value(j+1, getColumnIndex(sheet1,'风电场%d船%d可作业时间'%(k+1,i+1)))))

    # # 风机坐标
    # data['wind_farm']['coordinate']=[]
    # for i in range(count_wind_farm):
    #     data['wind_farm']['coordinate'].append([])
    #     for j in range(72):
    #         turbine_x = sheet1.cell_value(j+1, getColumnIndex(sheet1, '风机坐标X'))
    #         turbine_y = sheet1.cell_value(j+1, getColumnIndex(sheet1, '风机坐标Y'))
    #         data['wind_farm']['coordinate'][i].append((turbine_x, turbine_y))

    # 风机坐标
    data['wind_farm']['coordinate']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['coordinate'].append([])
        for j in range(count_wind_turbine_sum[i]):
            turbine_x = sheet1.cell_value(j+1+count_turbine[i], getColumnIndex(sheet1, '风机坐标X'))
            turbine_y = sheet1.cell_value(j+1+count_turbine[i], getColumnIndex(sheet1, '风机坐标Y'))
            data['wind_farm']['coordinate'][i].append((turbine_x, turbine_y))


    data['wind_farm']['task']=[]
    for i in range(count_wind_farm):
        data['wind_farm']['task'].append([])
        for j in range(count_wind_turbine[i]):
            data['wind_farm']['task'][i].append(int(sheet1.cell_value(j+1+count_wturbine[i], getColumnIndex(sheet1,'需要维修风机编号'))))

    return data


class_vessels = {0: '#FF0000', 1: '#008000', 2: '#0000FF'}  # 船的类型不同画出来的线颜色不同
# 风机分布,默认基站为X[0]Y[0]风机,这里便于观察需要对画图进行调整，法1：调换X,Y坐标系，法2：虚拟港口
X=sheet1.col_values(getColumnIndex(sheet1, '风机坐标X'))
Y=sheet1.col_values(getColumnIndex(sheet1, '风机坐标Y'))
# 去掉第一行参数名
del(X[0])
del(Y[0])

def vision_plot(num_day, num_vessel, num_wf,plan_path):
    # 打开交互模式
    plt.ion()
    # base = (2245000, 434600) #实际港口
    base=(2266000,452500) #虚拟港口
    # plt.plot(X, Y,'o')
    # plt.plot(base[0],base[1],'o')
    # plt.title('wind farm')
    # plt.savefig('/Users/Leung/Desktop/project/wind_farm.jpg')
    # plt.show()

    # 循环
    for iday in num_day:
        # 清除原有图像
        plt.cla()
        #
        plt.title("Day " + str(iday))
        # 画风机坐标图
        plt.plot(X, Y, 'o')
        # 标风机编号
        for i in range(len(X)):
            plt.text(X[i], Y[i], "%d" % (i), fontsize=10, color="r", style = "italic", weight = "light",verticalalignment='center', horizontalalignment='right', rotation=0) # 给散点加标签

        plt.plot(base[0], base[1], 'o')
        # 网格
        plt.grid(True)

        # 画不同船只的行进路线
        for ivessels in range(num_vessel):
            for iwf in range(num_wf):
                iplan_path = plan_path[ivessels][iwf][iday]
                # print('iplan_path:', iplan_path)
                if iplan_path == []:
                    continue

            # 这里是在画路线
                last_turbine = iplan_path[0]
                plt.plot([base[0], X[last_turbine]], [base[1], \
                                                      Y[last_turbine]], c=class_vessels[ivessels], marker='o')
                for turbine in iplan_path[1:]:
                    plt.plot([X[last_turbine], X[turbine]], [Y[last_turbine], \
                                                             Y[turbine]], c=class_vessels[ivessels], marker='o')
                    # 暂停
                    last_turbine = turbine
                plt.plot([X[last_turbine], base[0]], [Y[last_turbine], \
                                                      base[1]], c=class_vessels[ivessels], marker='o')
                # plt.pause(2)
        plt.title('routes: Day %d' % (iday))
        # plt.savefig('/Users/Leung/Desktop/project/1/routes_day%d.jpg' % (iday))
        plt.savefig('/Users/liuzx/Desktop/智能调度决策——自动化所/自动化所——调度/代码新/routes_day%d.jpg' % (iday))
        plt.show()
        # 关闭交互模式
    plt.ioff()

    return


def main():
    data = create_data_model()
    print(data)
    ############# Gurobi ##################
    T1 = time.perf_counter()
    opt_GB = MasterProblem_GB1(data)
    [cost, routes_index] = opt_GB.Mater_Problem()
    T2 = time.perf_counter()
    print('time: ', round(T2 - T1, 2))
    print('routes:\n', routes_index)# 这里的routes里的风机编号不是风机的真正编号，需要一个映射
    print('cost:\n',cost)
    # 风机在维修任务的索引与实际风机编号之间的映射
    routes=routes_index
    num_day = list(range(data['period']))
    num_vessel = len(data['vessel']['capacity'])
    num_wf=len(data['wind_farm']['maintenance_time'])
    # for iday in num_day:
    #     for ivessels in range(num_vessel):
    #         iplan_path = routes_index[ivessels][iday]
    #         if iplan_path == []:
    #             continue
    #         for turbine_index in range(len(iplan_path)):
    #             routes[ivessels][iday][turbine_index]=data['wind_farm']['task'][0][iplan_path[turbine_index]]#这里之后还要更改 0这个表示的是base
    #风机哪天被什么船访问
    visited_turbine=[]
    for iwf in range(num_wf):
        visited_turbine.append([])
    visited_turbine_paixu=[]
    for iwf in range(num_wf):
        visited_turbine_paixu.append([])
    # 映射
    for iday in num_day:
        for iwf in range(num_wf):
            for ivessels in range(num_vessel):
                iplan_path=routes_index[ivessels][iwf][iday]
                if iplan_path==[]:
                    continue
                for turbine_index in range(len(iplan_path)):
                    routes[ivessels][iwf][iday][turbine_index]=data['wind_farm']['task'][iwf][iplan_path[turbine_index]]
                    if [routes[ivessels][iwf][iday][turbine_index],ivessels,iday] not in visited_turbine[iwf]:
                        visited_turbine[iwf].append([routes[ivessels][iwf][iday][turbine_index],ivessels,iday])

    for i in range(num_wf):
        for j in range(len(data['wind_farm']['task'][i])):
            flag=0
            kcount=-1
            for k in range(len(visited_turbine[i])):
                if data['wind_farm']['task'][i][j]==visited_turbine[i][k][0]:
                    flag=1
                    kcount=k
                    break
                else:
                    continue
            if flag==1:
                visited_turbine_paixu[i].append(visited_turbine[i][kcount])
                continue
    print('routes:\n',routes)
    print('visited_turbine\n',visited_turbine)
    print('visited_turbine\n', visited_turbine_paixu)





    for i in range(num_wf):
        dataframe = pd.DataFrame({'day': range(data['period']), 'WF%dV1'%(i+1): routes[0][i], 'WF%dV2'%(i+1): routes[1][i], 'WF%dV3'%(i+1): routes[2][i]})
        dataframe.to_csv("C:/Users/liuzx/Desktop/智能调度决策——自动化所/自动化所——调度/代码新/船的路线表_WF%d.csv"%(i+1), index=False, sep=',')
        dataframe1=pd.DataFrame({'turbine in need of repair':data['wind_farm']['task'][i],'turbine_deadline':data['wind_farm']['deadline'][i],'output[turbine index,vessel index,day]':visited_turbine_paixu[i]})
        dataframe1.to_csv("C:/Users/liuzx/Desktop/智能调度决策——自动化所/自动化所——调度/代码新/风机维护时间安排表_WF%d.csv"%(i+1), index=False, sep=',')

    # dataframe.to_csv("/Users/Leung/Desktop/project/1/solution.csv", index=False, sep=',')
    # dataframe.to_csv("C:/Users/liuzx/Desktop/智能调度决策——自动化所/自动化所——调度/代码新/solution.csv", index=False, sep=',')

    # to plot the solution
    # routes = [[[], [0, 0], [], [4, 4], []], [[1, 3, 3, 1], [7, 7], [], [2, 2, 6, 6], [5, 5]], [[], [], [], [], []]]
    if routes != None:
        num_day = list(range(data['period']))
        num_vessel = len(data['vessel']['capacity'])
        vision_plot(num_day, num_vessel, num_wf,routes)


if __name__ == "__main__":
    main()
