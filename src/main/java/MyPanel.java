import Objects.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {

    private List<Location> withHead;
    private List<Location> withoutHead;
    private List<Location> finalOrder;
    int centerX, centerY;  //垃圾中心的坐标

    MyPanel() {
        //点位分组
        withHead = new ArrayList<>();
        withoutHead = new ArrayList<>();
        finalOrder = new ArrayList<>();  //最终的输出顺序
        //垃圾中心的坐标
        int centerX = (int) Main.trashCenter.getLatitude();
        int centerY = (int) Main.trashCenter.getLatitude();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);        //初始化制图方法
        g.setColor(Color.RED);

        g.drawRect(centerX, centerY, 15, 15); //绘制垃圾处理中心
//        g.drawLine(  //垃圾处理中心到最远端
//                centerX,
//                centerY,
//                (int) Main.where.get(Main.where.size() - 1).getLongitude(),
//                (int) Main.where.get(Main.where.size() - 1).getLatitude());

        for (int i = 1; i < 10; i++) {
            Location point = Main.where.get(i);
            g.setColor(Color.BLUE);
            g.drawOval((int) point.getLongitude(), (int) point.getLatitude(), 10, 10);
            g.setColor(Color.BLACK);
            /*距离圆*/
            //int diameter = 2 * (int) Location.calDistance(Main.trashCenter, point);
            //g.drawOval(-diameter / 2, -diameter / 2, diameter, diameter); //距离圆
        }
        g.setColor(Color.GREEN);


        Main.where.get(0).setVisited(true);  //已经访问

        withHead.add(Main.where.get(0));
        for (int i = 1; i < Main.where.size(); i++) {
            Location current = Main.where.get(i);
            if (current.isDown() == Main.where.get(0).isDown())
                withHead.add(current);
            else
                withoutHead.add(current);
        }
        boolean maxDisLocationWithHead = withHead.contains(Main.where.get(Main.where.size() - 1)); //最远点是否在出发时访问

        //绘制出发时访问的点
//        for (int i = 0; i < withHead.size() - 1; i++) {
//            g.drawLine(
//                    (int) withHead.get(i).getLongitude(),
//                    (int) withHead.get(i).getLatitude(),
//                    (int) withHead.get(i + 1).getLongitude(),
//                    (int) withHead.get(i + 1).getLatitude()
//            );
//        }
//        if (!maxDisLocationWithHead) { //最远点不在出发时访问
//            g.drawLine( //head的最终点和最远点连接
//                    (int) withHead.get(withHead.size() - 1).getLongitude(),
//                    (int) withHead.get(withHead.size() - 1).getLatitude(),
//                    (int) withoutHead.get(withoutHead.size() - 1).getLongitude(),
//                    (int) withoutHead.get(withoutHead.size() - 1).getLatitude()
//            );
//            //回程
//            for (int i = withoutHead.size() - 1; i > 0; i--) {
//                g.drawLine(
//                        (int) withoutHead.get(i).getLongitude(),
//                        (int) withoutHead.get(i).getLatitude(),
//                        (int) withoutHead.get(i - 1).getLongitude(),
//                        (int) withoutHead.get(i - 1).getLatitude()
//                );
//            }
//
//
//        } else { //在出发时访问最远点
//            g.drawLine(
//                    (int) withHead.get(withHead.size() - 1).getLongitude(),
//                    (int) withHead.get(withHead.size() - 1).getLatitude(),
//                    (int) withoutHead.get(withoutHead.size() - 1).getLongitude(),
//                    (int) withoutHead.get(withoutHead.size() - 1).getLatitude()
//            );
//            for (int i = withoutHead.size() - 1; i > 0; i--) {
//                g.drawLine(
//                        (int) withoutHead.get(i).getLongitude(),
//                        (int) withoutHead.get(i).getLatitude(),
//                        (int) withoutHead.get(i - 1).getLongitude(),
//                        (int) withoutHead.get(i - 1).getLatitude()
//                );
//            }
//        }
//        g.drawLine(
//                (int) withoutHead.get(0).getLongitude(),
//                (int) withoutHead.get(0).getLatitude(),
//                centerX,
//                centerY
//        );

        //绘制起点到第一个小区
        g.drawLine(
                centerX,
                centerY,
                (int) Main.finalOrder.get(0).getLongitude(),
                (int) Main.finalOrder.get(0).getLatitude()
        );

        //绘制接下来的小区
        for (int i = 0; i < Main.finalOrder.size() - 1; i++) {
            g.drawLine(
                    (int) Main.finalOrder.get(i).getLongitude(),
                    (int) Main.finalOrder.get(i).getLatitude(),
                    (int) Main.finalOrder.get(i + 1).getLongitude(),
                    (int) Main.finalOrder.get(i + 1).getLatitude()
            );
        }
        //绘制最后一个小区回到起点
        g.drawLine( //最后一个小区到起点
                (int) Main.finalOrder.get(Main.finalOrder.size() - 1).getLongitude(),
                (int) Main.finalOrder.get(Main.finalOrder.size() - 1).getLatitude(),
                centerX,
                centerY
        );

        g.setColor(Color.red);
    }
}
