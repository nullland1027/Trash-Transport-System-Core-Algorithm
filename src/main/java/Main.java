import Objects.*;
import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main{

//    private MyPanel mp;
    public static Location trashCenter; //垃圾站点位置
    // public static List<Location> where;  //社区位置列表
    public static List<Community> communitiesList; //社区列表
    public static List<Location> communityLocationList;
    public static Random ranGenerator;
    public final static int numOfLocation = 15;
    public static List<Location> finalOrder = new ArrayList<>();  //最终到访顺序

    /*Main构造函数，初始化画板*/
//    public Main() {
//        mp = new MyPanel();
//        this.add(mp);
//        this.setSize(500, 500);
//        this.setLocation(200, 200);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }

    public static void main(String[] args) {

        trashCenter = new Location(0, 0); //定义垃圾中心
        ranGenerator = new Random();  //测试用随机生成器，后期删除
        // where = new ArrayList<>();
        communitiesList = new ArrayList<>();
        communityLocationList = new ArrayList<>(); //社区位置列表

        TrashCar car = new TrashCar( //厨余垃圾车
                10,
                10,
                2.5,
                new Location(120.1689, 30.2553),
                "Kitchen waste");


        //TODO 填充社区列表
        for (int i = 0; i < numOfLocation; i++) {
            communitiesList.add(
                    new Community(
                        new Location(ranGenerator.nextDouble() * 100, ranGenerator.nextDouble() * 100),
                        new Location(ranGenerator.nextDouble() * 100, ranGenerator.nextDouble() * 100)
                    )
            );
        }

        communitiesList = sortByCommunity(communitiesList); //社区远近排序


        //TODO 填充社区位置列表 先模拟测试
        for (int i = 0; i < numOfLocation; i++) {
            communityLocationList.add(communitiesList.get(i).getEntrance());
        }

        communityLocationList.add(0, trashCenter);  //[0]设置为垃圾回收站
        Hamilton hamilton = new Hamilton(communityLocationList);
        int[] ans = hamilton.getAnswer(communityLocationList); // 计算得到的访问次序


        // 控制台输出
        for (int i = 0; i < ans.length - 1; i++) {
            finalOrder.add(communityLocationList.get(ans[i]));
        }
        // 最终次序的输出 后期删除
        System.out.println("最终访问顺序为");
        for (Location location : finalOrder) {
            System.out.print(location + " -> ");
        }

        //TODO 逆向得到社区访问顺序
        List<Community> finalCommunity = new ArrayList<>();
        for (int i = 0; i < numOfLocation - 1; i++) {

        }

        //TODO 开始访问 一次装不下就分多次
        for (int i = 0; i < numOfLocation - 1; i++) { //排除最后一个垃圾回收站
            //
        }


//        // Main draw = new Main();  后期删除
//
//        String api_result = GaodeAPI.doGet( //高德API调用 仅针对两个点
//                GaodeAPI.GAODE_URL,
//                car.getLocation(),
//                new Location(120.08822, 30.30428),
//                4);
//        JSON jsonOBJ = JSON.parseObject(api_result); //JSON解析封装
//        System.out.println(jsonOBJ);
    }

    /**
     * 冒泡排序：按照距离的从近到远排序
     * @param listIn 未排序的列表<Location>
     * @return 排序完的列表
     */
    public static List<Location> sortByLocation(List<Location> listIn) {
        int length = listIn.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (Location.calDistance(listIn.get(i), trashCenter) > Location.calDistance(listIn.get(j), trashCenter)) {
                    Location tmp = listIn.get(i);
                    listIn.set(i, listIn.get(j));
                    listIn.set(j, tmp);
                }
            }
        }
        return listIn;
    }

    /**
     * 按照小区从近到远排序
     * @param listIn 输入的社区列表<Community>
     * @return 排序完成的列表
     */
    public static List<Community> sortByCommunity(List<Community> listIn) {
        int length = listIn.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (
                        Location.calDistance(listIn.get(i).getEntrance(), trashCenter)
                        >
                        Location.calDistance(listIn.get(j).getEntrance(), trashCenter)
                ) {
                    Community tmp = listIn.get(i);
                    listIn.set(i, listIn.get(j));
                    listIn.set(j, tmp);
                }
            }
        }
        return listIn;
    }

}



