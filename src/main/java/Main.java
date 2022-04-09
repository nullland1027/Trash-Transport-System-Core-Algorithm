import Objects.*;
import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main{

    /**
     * 调用此方法
     * @param trashCenter 垃圾车的位置
     * @param communitiesList 需要清理垃圾的社区列表
     * @param numOfLocation 社区数量
     * @param car 垃圾车
     * @return 访问次序的路径规划json数据列表
     */
    public static List<JSON> callIt(
            Location trashCenter,
            List<Community> communitiesList,
            int numOfLocation,
            TrashCar car
    ) {
        List<Location> finalOrder = new ArrayList<>();  //最终到访顺序
        List<Location>communityLocationList = new ArrayList<>(); //社区位置列表

        communitiesList = sortByCommunity(communitiesList, trashCenter); //社区远近排序


        //TODO 填充社区位置列表 先模拟测试
        for (int i = 0; i < numOfLocation; i++) {
            communityLocationList.add(communitiesList.get(i).getEntrance());
        }

        communityLocationList.add(0, trashCenter);  //[0]设置为垃圾回收站


        Hamilton hamilton = new Hamilton(communityLocationList);
        int[] ans = hamilton.getAnswer(communityLocationList, numOfLocation); // 计算得到的访问次序


        // 控制台输出
        for (int i = 0; i < ans.length - 1; i++) {
            finalOrder.add(communityLocationList.get(ans[i]));
        }
        // 最终次序的输出 后期删除
        System.out.println("最终访问顺序为");
        for (Location location : finalOrder) {
            System.out.print(location + " -> ");
        }
        System.out.println();


        // 逆向得到社区访问顺序
        List<Community> finalCommunity = getFinalCommunitiesList(communitiesList, finalOrder);

        for (Community i : finalCommunity) {
            System.out.println(i);
        }


//        String api_result = GaodeAPI.doGet( //高德API调用 仅针对两个点
//                GaodeAPI.GAODE_URL,
//                car.getLocation(),
//                new Location(120.08822, 30.30428),
//                4);
//        JSON jsonOBJ = JSON.parseObject(api_result); //JSON解析封装
//        System.out.println(jsonOBJ);


        return null;
    }

    public static void main(String[] args) {
        /**/
        int numOfLocation = 11;
        Random ranGenerator = new Random();  //测试用随机生成器，后期删除
        Location trashCenter = new Location(0, 0);
        TrashCar trashCar = new TrashCar(20, 20, 4, new Location(0, 0), "3");
        List<Community> communitiesList = new ArrayList<>();

        //TODO 填充社区列表
        for (int i = 0; i < numOfLocation; i++) {
            communitiesList.add(
                    new Community(
                            new Location(ranGenerator.nextDouble() * 100, ranGenerator.nextDouble() * 100),
                            new Location(ranGenerator.nextDouble() * 100, ranGenerator.nextDouble() * 100)
                    )
            );
        }

        /**
         * 调用此方法
         */
        callIt(
                trashCenter,
                communitiesList,
                numOfLocation,
                trashCar
        );
    }

    /**
     *
     * @return 社区访问顺序列表
     */
    public static List<Community> getFinalCommunitiesList(List<Community> communitiesList, List<Location> finalOrder) {
        List<Community> ans = new ArrayList<>();
        for (int i = 0; i < communitiesList.size(); i++) {
            for (int j = 0; j < communitiesList.size(); j++) {
                if (communitiesList.get(j).getEntrance().equals(finalOrder.get(i))) {
                    ans.add(communitiesList.get(j));
                }
            }
        }


        return ans;
    }

    /**
     * 冒泡排序：按照距离的从近到远排序
     * @param listIn 未排序的列表<Location>
     * @return 排序完的列表
     */
    public static List<Location> sortByLocation(List<Location> listIn, Location trashCenter) {
        int length = listIn.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (
                        Location.calDistance(listIn.get(i), trashCenter)
                        >
                        Location.calDistance(listIn.get(j), trashCenter)
                ) {
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
    public static List<Community> sortByCommunity(List<Community> listIn, Location trashCenter) {
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



