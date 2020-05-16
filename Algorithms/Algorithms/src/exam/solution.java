package exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class solution {

    public static void main(String [] args){

        solution closestDistance = new solution();
        closestDistance.getInput();

    }

    public  void getInput(){
        Scanner scn = new Scanner(System.in);

        int noOfTestCase = scn.nextInt();
        int noOfPoints = scn.nextInt();

        for(int i=0; i < noOfTestCase; i++){
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            List<Point> pointList = new ArrayList<>();
            
            for(int j=0; j< noOfPoints; j++) {
                int x = scn.nextInt();
                maxX = Math.max(maxX, x);
                int y = scn.nextInt();
                maxY = Math.max(maxY, y);
                
                Point p = new Point(x, y);
                pointList.add(p);
            }

            calculateMinimumDistance(pointList,maxX+1, maxY+1);
        }
    }

    public void calculateMinimumDistance(List<Point> pointList, int maxX, int maxY){

        int [][] minimumDistance = new int [maxX][maxY];

        for(int i=0;i<maxX;i++){
            for(int j=0;j<maxY;j++){

                int sum =0;
                for(Point p:pointList){
                    sum=sum+Math.abs(p.x-i)+Math.abs(p.y-j);

                }
                minimumDistance[i][j]=sum;
            }
        }

        int min =Integer.MAX_VALUE;
        for(int i=0;i<maxX;i++){
            for(int j=0;j<maxY;j++){

                if(minimumDistance[i][j]<=min){
                    min=minimumDistance[i][j];
                }

            }
        }

        int count =0;

        for(int i=0;i<maxX;i++){
            for(int j=0;j<maxY;j++){
                if(minimumDistance[i][j]==min)
                    count++;

            }
        }

        System.out.print(min+" ");
        System.out.println(count);
    }


    class Point{
        int x;
        int y;


        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}