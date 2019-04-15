package eg.edu.alexu.csd.datastructure.iceHockey22;
import java.awt.*;
import java.lang.*;
import java.util.*;
public interface IPlayersFinder {
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}
 class solution implements IPlayersFinder{
     static int count=0;
     static Vector point;
     @Override
     public Point[] findPlayers(String[] photo, int team, int threshold) {
         int rows=photo.length;
         int col=photo[0].length();
         boolean[][] visited=new boolean[rows][col];
         for(int i=0;i<rows;i++){
             for(int j=0;j<col;j++){
                 visited[i][j]=false;
             }
         }
         for(int i=0;i<rows;i++){
             String temp=photo[i];
             for(int j=0;j<col;j++){
                 if(temp.charAt(j)==team && visited[i][j]==false){
                     check(photo,visited,team,threshold,i,j);
                 }
             }
         }
         int n = point.size();Point[] ans=new Point[n];
         for(int i=0;i<ans.length;i++){
             ans[i]=point.elementAt(i);
         }
         for(int i=0;i<ans.length;i++){
             for(int j=i+1;j<ans.length;j++){
                 if(ans[i].getX()>ans[j].getX()){
                     Point test=new Point();
                     test=ans[i];
                     ans[i]=ans[j];
                     ans[j]=test;
                 }
             }
         }
         for(int i=0;i<ans.length;i++){
             for(int j=i+1;j<ans.length;j++){
                 if(ans[i].getX()==ans[j].getX()&&ans[i].getY()>ans[j].getY()){
                     Point test=new Point();
                     test=ans[i];
                     ans[i]=ans[j];
                     ans[j]=test;
                 }
             }
         }
         return ans;
     }

     public static void check(String[] photo,boolean[][] visited,int team,int threshold,int i,int j){
         int rows=photo.length;int xmax=0;int xmin=0;
         int col=photo[0].length();int ymax=0;int ymin=0;
         visited[i][j]=true;
         String temp=photo[i];
         if(i+1>=0 && i+1<rows){
             if(temp.charAt(i+1)==team && visited[i+1][j]==false){
                 ymax=i+1;count++;
                 check(photo,visited,team,threshold,i+1,j);
             }
         }
         if(i-1>=0 && i-1<rows){
             if(temp.charAt(i-1)==team && visited[i-1][j]==false){
                 ymin=i-1;count++;
                 check(photo,visited,team,threshold,i-1,j);
             }
         }
         if(j+1>=0 && j+1<col){
             if(temp.charAt(j+1)==team && visited[i][j+1]==false){
                 xmax=j+1;count++;
                 check(photo,visited,team,threshold,i,j+1);
             }
         }
         if(j-1>=0 && j-1<col){
             if(temp.charAt(j-1)==team && visited[i][j-1]==false){
                 xmin=j-1;count++;
                 check(photo,visited,team,threshold,i,j-1);
             }
         }
         if(count>=Math.ceil(threshold/4)) {
             xmax = ((2 * xmax + 2) - 2 * xmin) / 2;
             ymax = ((2 * ymax + 2) - 2 * ymin) / 2;
             Point couple=new Point(xmax,ymax);
             point.add(couple);
         }
         count=0;

     }
 }