import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main extends JFrame {

    private MyPanel mp;
    public static Location trashCenter;
    public static List<Location> where;  //垃圾点位
    public static Random ranGenerator;
    public final static int numOfLocation = 10;
    public static List<Location> finalOrder = new ArrayList<>();  //最终到访顺序

    public Main() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(500, 500);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        trashCenter = new Location(0, 0);
        ranGenerator = new Random();
        where = new ArrayList<>();
        for (int i = 0; i < numOfLocation; i++) {
            where.add(new Location(400 * ranGenerator.nextDouble(), 400 * ranGenerator.nextDouble()));
        }
        where = sort(where);
        where.add(0, trashCenter);
        Hamilton hamilton = new Hamilton(where);
        int[] ans = hamilton.getAnswer(where);
        for (int i = 0; i < ans.length - 1; i++) {
            System.out.println(where.get(ans[i])); //垃圾点到访顺序
            finalOrder.add(where.get(ans[i]));
        }


        Main draw = new Main();
    }

    /**
     * 按照距离垃圾回收站的从近到远排序
     * @param listIn 未排序的列表
     * @return 排序完的列表
     */
    public static List<Location> sort(List<Location> listIn) {
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
}



