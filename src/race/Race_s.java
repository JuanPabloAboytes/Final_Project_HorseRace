/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author JUANPABLO
 */
public class Race_s extends Thread{
    private JLabel eti;
    private RaceFrame2 r;
    private JLabel p1_score;
    private JLabel p2_score;
    private int p1_s;
    private int p2_s;
    private JLabel comments_label; 
    public int number_r;
    
    public Race_s(JLabel eti, RaceFrame2 r,JLabel p1_score, int p1_s,JLabel p2_score, int p2_s,JLabel comments_label,int number_r){
        this.eti = eti;
        this.r = r;
        this.p1_score = p1_score;
        this.p2_score = p2_score;
        this.p1_s = 0;
        this.p2_s = 0;
        this.comments_label = comments_label;
        this.number_r = number_r;
    }

    public int getNumber_r() {
        return number_r;
    }
        
    synchronized void setNumber_r(int r) {
    	number_r += 1;
    }
    
    public void run(){
        int c1=0, c2=0;
        int step = 0;
        int r_event = 0;
        String comments;
        Random rn = new Random();
        boolean playing = true;
        
        while(playing == true){
            try {
                sleep((int)(Math.random()*1000));
                step = rn.nextInt(10) + 1;
                r_event = rn.nextInt(10) + 1;
                c1=r.getPlayer1().getLocation().x;
                c2=r.getPlayer2().getLocation().x;
                
                if(c1<r.getFinal_line().getLocation().x-10&&c2<r.getFinal_line().getLocation().x-10){
                    if(r_event == 1){  //&& eti.getLocation().x 
                        eti.setLocation(eti.getLocation().x-step,eti.getLocation().y);
                        r.repaint();
                    } else if (r_event > 1 && r_event < 10){
                        eti.setLocation(eti.getLocation().x+step,eti.getLocation().y);
                        r.repaint();
                    } else if (r_event == 10){
                        sleep((int)(Math.random()*1000));
                        r.repaint();
                    }

                }else{
                }
                
                
            } catch (InterruptedException e) {
            }
            
            //if a horse is in the finish line...
            if(eti.getLocation().x>=r.getFinal_line().getLocation().x-10){
                //player1 won
                if(c1>c2){
                    p1_s +=1;
                    String str1 = Integer.toString(p1_s); 
                    p1_score.setText(str1);
                    String comments_string = Integer.toString(number_r);
                    comments = "Player 1 won his race number: " +comments_string;
                    setNumber_r(number_r);
                    comments_label.setText(comments);
                    comments_label.setVisible(true);
                    
                } else if(c2>c1){ //player 2 won
                    p2_s +=1;
                    String str2 = Integer.toString(p2_s);
                    p2_score.setText(str2);
                    String comments_string2 = Integer.toString(number_r);
                    comments = "Player 2 won his race number: " +comments_string2;
                    setNumber_r(number_r);
                    
                    comments_label.setText(comments);
                    comments_label.setVisible(true);
                }else{
                    comments = "Tied ";
                    setNumber_r(number_r);
                    p2_s +=1;
                    p1_s +=1;
                    comments_label.setText(comments);
                    comments_label.setVisible(true);
                    
                }
            }
            
            if (p1_s == 2 && number_r<5){
                r.setVisible(false);
                JOptionPane.showMessageDialog(null, "Player 1 wins");
                playing = false;
                End end = new End();
                RaceFrame2 race = new RaceFrame2();
                end.setVisible(true);
                
            } else if (p2_s == 2 && number_r<5){
                r.setVisible(false);
                JOptionPane.showMessageDialog(null, "Player 2 wins");
                playing = false;
                End end = new End();
                RaceFrame2 race = new RaceFrame2();
                end.setVisible(true);
            }else if (p2_s == 2 && p1_s == 2 && number_r<5){
                r.setVisible(false);
                JOptionPane.showMessageDialog(null, "Tied");
                playing = false;
                End end = new End();
                end.setVisible(true);
            }            
        }
    }

}
