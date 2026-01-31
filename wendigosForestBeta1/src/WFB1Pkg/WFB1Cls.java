package WFB1Pkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WFB1Cls {
	
	public static int second = 1000000000;
	
    static class KeyListen
        extends KeyAdapter // Apologies, when i sent the code to myself it went
                           // double spaces because Gmail sucks.

    {
        @Override

        public void keyTyped(KeyEvent event)

        {
            char ch = event.getKeyChar();

            if (ch == 'w' || ch == 'a' || ch == 's' || ch == 'd' || ch == 'p'
                || ch == 'q' || ch == 'e' || ch == 'r' || ch == '0' || ch == '1'
                || ch == '2' || ch == '3' || ch == '4')

            {
                actio.n += event.getKeyChar();
            }

            if (event.getKeyCode() == KeyEvent.VK_HOME)

            {
                actio.n = ("Key codes: "
                    + event.getKeyCode()); // I'm not completely sure what this
                                           // does to be honest, but I'm too
                                           // scared to delete it.
            }
        }

    } /* ^ I ripped most of this class from a web site, but it's OK because god
       punished

       * me by making the formatting disgusting so i had to fix it

       */

    public static class actio {
        public static String n =
            ""; // See what i did there? came in handy in replacing its
                // obsoleted "action" predecessor string

        public static int health = 100;

        public static int munition = 20;
    }

    public static boolean collisionCheck(boolean[][] collision, int x, int y) {
        return collision[x + 1][y + 1];
    }

    public static String enemyPathFinding

        (char[][] map, boolean[][] collision, int BUeX, int BUeY, int coordX,
            int coordY, boolean PBClear,

            boolean PSClear, int enmX, int enmY, int LlocationX,
            int LlocationY) { // Oh boy... i'm sorry for the absolute volume of
                              // this monster but i had to do it.

        // If you get lost, CtrlCV this: "map, collision, BUeX DOEU, BUeY DOEU,
        // coordX, coordY, PBClearDOEU, PSClearDOEU, enmX DOEU, enmY DOEU,
        // LlocationX DOEU, LlocationY DOEU"

        // PS: DOEU means "depends on enemy used", as in reusing this with
        // different enemies will require change in this value.

        boolean dingus = true;

        do

        {
            if (PBClear == true) // LARGE POSITIVE CHANGE (invalidated by large
                                 // negative change)

            {
                if (Math.abs(coordY - enmY) <= Math.abs(
                        coordX - enmX)) // if y distance is more exacting...

                {
                    if (enmX > coordX)

                        enmX--;

                    else

                        enmX++; // go the more general direction x.

                }

                else if (Math.abs(coordX - enmX) < Math.abs(coordY
                             - enmY)) // if x distance is more exacting...

                {
                    if (enmY > coordY)

                        enmY--;

                    else

                        enmY++; // go the more general direction y.
                }

                if (collisionCheck(collision, enmX, enmY)
                    == false) // if this worked, you can try the other axis of
                              // movement again.

                    PSClear = true;
            }

            if ((collisionCheck(collision, enmX, enmY) == true
                    && PSClear == true)
                || (enmX == LlocationX
                    && enmY == LlocationY)) // if this direction didn't work,
                                            // try another one

            { // SMALL POSITIVE CHANGE (invalidated by small negative change)

                enmX = BUeX;

                enmY = BUeY;

                if (Math.abs(coordX - enmX) < Math.abs(
                        coordY - enmY)) // try the smaller progressing distance

                {
                    if (enmX > coordX)

                        enmX--;

                    else

                        enmX++;

                }

                else if (Math.abs(coordY - enmY) <= Math.abs(coordX - enmX))

                {
                    if (enmY > coordY)

                        enmY--;

                    else

                        enmY++;
                }

                if (collisionCheck(collision, enmX, enmY)
                    == false) // if this worked, you can try the other axis of
                              // movement again.

                    PBClear = true;
            }

            if ((collisionCheck(collision, enmX, enmY) == true)
                || (enmX == LlocationX
                    && enmY == LlocationY)) // if this direction didn't work,
                                            // try a 'bad' direction, but just a
                                            // minorly bad one

            { // SMALL NEGATIVE CHANGE

                enmX = BUeX;

                enmY = BUeY;

                PSClear = false;

                if (Math.abs(coordX - enmX) < Math.abs(
                        coordY - enmY)) // try the smaller declining distance

                {
                    if (enmX > coordX)

                        enmX++;

                    else

                        enmX--;

                }

                else if (Math.abs(coordY - enmY) <= Math.abs(coordX - enmX))

                {
                    if (enmY > coordY)

                        enmY++;

                    else

                        enmY--;
                }

                if (enmX > 9 && enmY > 9 && enmX < 0 && enmY < 0)

                    if (collision[enmX][enmY]
                        == false) // if this worked, you can try the other axis
                                  // of movement again.

                        PBClear = true;
            }

            if ((collisionCheck(collision, enmX, enmY) == true)
                || (enmX == LlocationX
                    && enmY == LlocationY)) // if this didn't work, try the
                                            // final remaining option

            { // LARGE NEGATIVE CHANGE

                enmX = BUeX;

                enmY = BUeY;

                PBClear = false;

                if (Math.abs(coordY - enmY) <= Math.abs(
                        coordX - enmX)) // try the larger declining distance.

                {
                    if (enmX > coordX)

                        enmX++;

                    else

                        enmX--;

                }

                else if (Math.abs(coordX - enmX) < Math.abs(coordY - enmY))

                {
                    if (enmY > coordY)

                        enmY++;

                    else

                        enmY--;
                }

                if (enmX > 9 && enmY > 9 && enmX < 0 && enmY < 0)

                    if (collisionCheck(collision, enmX, enmY)
                        == false) // if this worked, you can try the other axis
                                  // of movement again.

                        PSClear = true;
            }

            if (collisionCheck(collision, enmX, enmY)
                == true) // this will happen if the AI traps itself in a 3 sided
                         // corner after changing direction to go away from the
                         // player.

            {
                enmX = BUeX;

                enmY = BUeY;

                PBClear = true;

                PSClear = true; // emergency measures

                dingus = false;

            }

            else
                dingus = true;

        } while (dingus == false);

        String changedVals = "";

        if (PBClear == true)

            changedVals += "Y";

        else
            changedVals += "N";

        if (PSClear == true)

            changedVals += "Y";

        else
            changedVals += "N";

        changedVals += enmX;

        changedVals += enmY;

        return changedVals;
    }

    public static double dmgFind(double damage, boolean[][] collision,
        double[] wpnFalloff, double[] wpnPenetration, int weaponChoice,
        int depth, int width) { //TODO: This function is poorly implemented. Make an array index of weapons and their ID#
        // dmgFind(damage, collision, wpnFalloff, wpnPenetration, weaponChoice,
        // X, Y)

        if (depth > 9 || width > 9)

            return 0;

        damage *= (wpnFalloff[weaponChoice] / 100);

        if (collisionCheck(collision, depth, width) == true)

            damage *= (wpnPenetration[weaponChoice] / 100);

        return damage;
    }

    public static void mapGen(
        char[][] mapLog, char[][] map, boolean[][] collision) {
        for (int x = 0; x < 10; x++)

        {
            for (int y = 0; y < 10; y++)

            {
                mapLog[x][y] = map[x][y];
            }
        }

        for (int x = 0; x < 12; x++)

        {
            for (int y = 0; y < 12; y++)

            {
                collision[x][y] = true;
            }
        }

        for (int x = 0; x < 10; x++)

        {
            for (int y = 0; y < 10; y++)

            {
                if (map[x][y] == ' ' || map[x][y] == '/')

                    collision[x + 1][y + 1] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int

            weaponChoice = 10,

            eHealth = 100,

            coordX = 2, coordY = 2,

            enmX = 8, enmY = 8,

            BUcX = coordX, BUcY = coordY, BUeX = enmX, BUeY = enmY,

            LlocationX = 0, LlocationY = 0;

        boolean[][] collision = new boolean[12][12];

        boolean pause = false, PBClear = true, PSClear = true, rewarded = false;

        char[][] map = new char[10][10], mapLog = new char[10][10];

        String[] displayRows = new String[10];

        String[] wpnNames = new String[10];

        double[] wpnDmg = new double[10];

        double[] wpnFalloff = new double[10];

        double[] wpnPenetration = new double[10];

        wpnNames[0] = "Pistol"; 

        wpnDmg[0] = 20;

        wpnFalloff[0] = 85;

        wpnPenetration[0] = 0;

        wpnNames[1] = "Shotgun";

        wpnDmg[1] = 40;

        wpnFalloff[1] = 70;

        wpnPenetration[1] = 0;

        wpnNames[2] = "Hunting rifle";

        wpnDmg[2] = 30;

        wpnFalloff[2] = 90;

        wpnPenetration[2] = 50;

        wpnNames[3] = "Revolver";

        wpnDmg[3] = 15;

        wpnFalloff[3] = 80;

        wpnPenetration[3] = 70;

        String display = "";

        /*File map0 = new
        File(Thread.currentThread().getContextClassLoader().getResource("Map0.txt").toURI());//Why
        did no one tell me you could just use this

        Scanner input0 = new Scanner(map0);

        */

        Scanner input0 = new Scanner(new File(Thread.currentThread()
                                                  .getContextClassLoader()
                                                  .getResource("Map0.txt")
                                                  .toURI()));

        JFrame f = new JFrame();

        f.setBounds(500, 500, 1000, 1000);

        JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel(),
               p4 = new JPanel();

        JLabel v1 = new JLabel(" ");

        v1.setAlignmentX(Component.CENTER_ALIGNMENT);

        v1.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v1.setForeground(Color.orange);

        JLabel v2 = new JLabel(" ");

        v2.setAlignmentX(Component.CENTER_ALIGNMENT);

        v2.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v2.setForeground(Color.orange);

        JLabel v3 = new JLabel(" ");

        v3.setAlignmentX(Component.CENTER_ALIGNMENT);

        v3.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v3.setForeground(Color.orange);

        JLabel v4 = new JLabel(" ");

        v4.setAlignmentX(Component.CENTER_ALIGNMENT);

        v4.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v4.setForeground(Color.orange);

        JLabel v5 = new JLabel("You awaken in a strange forest,");

        v5.setAlignmentX(Component.CENTER_ALIGNMENT);

        v5.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v5.setForeground(Color.orange);

        JLabel v6 = new JLabel("all by yourself.");

        v6.setAlignmentX(Component.CENTER_ALIGNMENT);

        v6.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v6.setForeground(Color.orange);

        JLabel v7 = new JLabel(" ");

        v7.setAlignmentX(Component.CENTER_ALIGNMENT);

        v7.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v7.setForeground(Color.orange);

        JLabel v8 = new JLabel("You must leave this place.");

        v8.setAlignmentX(Component.CENTER_ALIGNMENT);

        v8.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v8.setForeground(Color.orange);

        JLabel v9 = new JLabel(" ");

        v9.setAlignmentX(Component.CENTER_ALIGNMENT);

        v9.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v9.setForeground(Color.orange);

        JLabel v0 = new JLabel("Alive.");

        v0.setAlignmentX(Component.CENTER_ALIGNMENT);

        v0.setFont(new Font("Andale Mono", Font.PLAIN, 20));

        v0.setForeground(Color.orange);

        JLabel text = new JLabel("WENDIGO'S FOREST");

        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        text.setFont(new Font("Serif", Font.PLAIN, 30));

        text.setForeground(Color.red);

        JLabel healthDsp =
            new JLabel("Health: " + String.valueOf(actio.health));

        healthDsp.setFont(new Font("Consolas", Font.BOLD, 20));

        healthDsp.setForeground(Color.black);

        JLabel ammoDsp =
            new JLabel("munitions: " + String.valueOf(actio.munition));

        ammoDsp.setFont(new Font("Consolas", Font.BOLD, 20));

        ammoDsp.setForeground(Color.black);

        JTextArea newInput = new JTextArea(3, 20);

        newInput.setForeground(Color.black);

        newInput.setBackground(Color.black);

        newInput.setSize(250, 250);

        newInput.setLineWrap(true);

        newInput.setWrapStyleWord(true);

        newInput.addKeyListener(new KeyListen());

        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        p1.setBackground(Color.black);

        p1.add(v1);

        p1.add(v2);

        p1.add(v3);

        p1.add(v4);

        p1.add(v5);

        p1.add(v6);

        p1.add(v7);

        p1.add(v8);

        p1.add(v9);

        p1.add(v0);

        p1.add(healthDsp);

        p1.add(ammoDsp);

        p2.setBorder(BorderFactory.createEmptyBorder(100, 125, 0, 125));

        p2.add(newInput);

        p2.setBackground(Color.black);

        p3.setBorder(BorderFactory.createEmptyBorder(10, 0, 200, 0));

        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        p3.add(text);

        p3.setBackground(Color.black);

        f.setLayout(new BorderLayout());

        f.add(p1, BorderLayout.CENTER);

        f.add(p2, BorderLayout.SOUTH);

        f.add(p3, BorderLayout.NORTH);

        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // use from here to the mapGen method for map loading.

        //

        for (int x = 0; x < 10; x++)

        {
            String row = input0.nextLine();

            for (int y = 0; y < 10; y++)

            {
                if (row.charAt(y) != '1')

                    map[x][y] = row.charAt(y);

                else

                {
                    map[x][y] = ' ';

                    enmX = x;

                    enmY = y;

                    eHealth = 100;

                    rewarded = false;
                }
            }
        }

        mapGen(mapLog, map, collision);

        //

        map[2][2] = 'X';

        map[9][9] = 'Y';

        Thread.sleep(5000);

        long playMClock = System.nanoTime();

        long AIMClock =
            System.nanoTime(); // Player and AI movement clock (all AI entities
                               // run on same clock unless new clock specified)

        long AIBadLocationClock =
            System.nanoTime(); // useful for AI pathfinding, makes enemies less
                               // likely to retrace steps

        v1.setText("Choose your weapon (e to shoot)");

        v2.setText("1 = Pistol");

        v3.setText("2 = Shotgun");

        v4.setText("3 = Hunting Rifle");

        v5.setText("4 = Revolver");

        v6.setText("1.  Good all-rounder with decent damage and manageable falloff.");

        v7.setText("2.  Devistating at close range, but weak when far away.");

        v8.setText("3.  Great damage, very long range, can pierce walls for 50% damage.");

        v9.setText("4.  Sacrifices damage and range to pierce walls, maintaining 70% damage.");

        v0.setText("Melee coming soon...");

        do

        { // TODO: figure out why this breaks upon keyboard mashing, but WASD controls don't.
            Thread.sleep(1000);

            for (int x = 0; x < actio.n.length(); x++)

            {
            	int choice = (actio.n.charAt(x) - 49);
            	weaponChoice = (choice > -1 || choice < 4) ? choice : 10;
            	if (weaponChoice != 10) {
            		v1.setText("You have chosen " + wpnNames[choice]);
            		Thread.sleep(4000);
                    break;
            	}
            }
        } while (weaponChoice == 10);

        healthDsp.setForeground(Color.red);

        ammoDsp.setForeground(Color.red);

        do

        {
            while (pause == true) // Pause program

            {
                playMClock = System.nanoTime();

                AIMClock = System.nanoTime();

                for (int x = 0; x < actio.n.length(); x++)

                {
                    if (actio.n.charAt(x) == 'p')

                    {
                        actio.n = "";

                        pause = false;
                    }
                }
            }

            if (actio.health <= 0)

            {
                v1.setText(" ");

                v2.setText(" ");

                v3.setText(" ");

                v4.setText(" ");

                v5.setText(" ");

                v5.setText(" ");

                v6.setText(" ");

                v7.setText(" ");

                v8.setText(" ");

                v9.setText("You have been finished.");

                v0.setText("The program will now terminate.");

                Thread.sleep(7000);

                f.dispose();
                
                input0.close();

                return;
            }

            if (System.nanoTime() - playMClock >= second * 0.25)

            {
                if ((Math.abs(coordX - enmX) < 2 && Math.abs(coordY - enmY) < 2)
                    && eHealth > 0) // Ouchie

                    actio.health -= 3;

                healthDsp.setText("Health: "
                    + String.valueOf(actio.health)); // does not need to be in
                                                     // the if statement

                for (int x = 0; x < 10; x++)

                {
                    for (int y = 0; y < 10; y++)

                    {
                        map[x][y] = mapLog[x][y];
                    }
                }

                BUcX = coordX;

                BUcY = coordY;

                BUeX = enmX;

                BUeY = enmY;

                for (int x = 0; x < actio.n.length(); x++)

                {
                    if (actio.n.charAt(x) == 'w')

                    {
                        coordX--;

                        break;

                    }

                    else if (actio.n.charAt(x) == 'a')

                    {
                        coordY--;

                        break;

                    }

                    else if (actio.n.charAt(x) == 's')

                    {
                        coordX++;

                        break;

                    }

                    else if (actio.n.charAt(x) == 'd')

                    {
                        coordY++;

                        break;

                    }

                    else if (actio.n.charAt(x) == 'p')

                    {
                        pause = true;

                        break;

                    }

                    else if (actio.n.charAt(x) == 'e'
                        && (Math.abs(coordX - enmX) < 2
                               && Math.abs(coordY - enmY) < 2)
                            == false
                        && actio.munition > 0) // Math.abs(coordY - enmY) ,
                                               // Math.abs(coordX - enmX)

                    {
                        actio.munition -= 1;

                        ammoDsp.setText(
                            "munitions: " + String.valueOf(actio.munition));

                        double wid = Math.abs(coordY - enmY),
                               dep = Math.abs(coordX - enmX),
                               shot = Math.sqrt(wid * wid + dep * dep);

                        int LONG, SHORT;

                        double damage = wpnDmg[weaponChoice];

                        char axe = 'o';

                        if (wid >= dep)

                        {
                            LONG = Math.abs(coordY - enmY) + 1;

                            axe = 'Y';

                            SHORT = Math.abs(coordX - enmX) + 1;

                        }

                        else

                        {
                            LONG = Math.abs(coordX - enmX) + 1;

                            axe = 'X';

                            SHORT = Math.abs(coordY - enmY) + 1;
                        }

                        double change = LONG / SHORT;

                        if (coordX != enmX && coordY != enmY)

                        {
                            if (axe == 'X')

                            {
                                if (enmX < coordX) // if the enemy is behind (x)

                                {
                                    if (enmY < coordY) // if enemy is behind (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordX
                                                    - Math.round(change * X))
                                                    > 9
                                                || (int) (coordX
                                                       - Math.round(change * X))
                                                    < 0
                                                || (coordY - X) > 9
                                                || (coordY - X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                ((int) Math.abs(coordX
                                                    - Math.round(change * X))),
                                                Math.abs(coordY - X));

                                            if (damage == 0)

                                                break;

                                            map[(int) (coordX
                                                - Math.round(change * (X)))]
                                               [coordY - X] = '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                    else // if enemy is ahead (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordX
                                                    - Math.round(change * X))
                                                    > 9
                                                || (int) (coordX
                                                       - Math.round(change * X))
                                                    < 0
                                                || (coordY + X) > 9
                                                || (coordY + X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                ((int) Math.abs(coordX
                                                    - Math.round(change * X))),
                                                Math.abs(coordY + X));

                                            if (damage == 0)

                                                break;

                                            map[(int) (coordX
                                                - Math.round(change * (X)))]
                                               [coordY + X] = '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                }

                                else // if the enemy is ahead (x)

                                {
                                    if (enmY < coordY) // if enemy is behind (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordX
                                                    + Math.round(change * X))
                                                    > 9
                                                || (int) (coordX
                                                       + Math.round(change * X))
                                                    < 0
                                                || (coordY - X) > 9
                                                || (coordY - X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                ((int) Math.abs(coordX
                                                    + Math.round(change * X))),
                                                Math.abs(coordY - X));

                                            if (damage == 0)

                                                break;

                                            map[(int) (coordX
                                                + Math.round(change * (X)))]
                                               [coordY - X] = '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                    else

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordX
                                                    + Math.round(change * X))
                                                    > 9
                                                || (int) (coordX
                                                       + Math.round(change * X))
                                                    < 0
                                                || (coordY + X) > 9
                                                || (coordY + X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                ((int) Math.abs(coordX
                                                    + Math.round(change * X))),
                                                Math.abs(coordY + X));

                                            if (damage == 0)

                                                break;

                                            map[(int) (coordX
                                                + Math.round(change * (X)))]
                                               [coordY + X] = '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }
                                }

                            }

                            else

                            {
                                if (enmX < coordX) // if the enemy is behind (x)

                                {
                                    if (enmY < coordY) // if enemy is behind (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordY
                                                    - Math.round(change * X))
                                                    > 9
                                                || (int) (coordY
                                                       - Math.round(change * X))
                                                    < 0
                                                || (coordX - X) > 9
                                                || (coordX - X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                Math.abs(coordX - X),
                                                ((int) Math.abs(coordY
                                                    - Math.round(change * X))));

                                            if (damage == 0)

                                                break;

                                            map[coordX - X][(int) (coordY
                                                - Math.round(change * (X)))] =
                                                '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                    else // if enemy is ahead (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordY
                                                    + Math.round(change * X))
                                                    > 9
                                                || (int) (coordY
                                                       + Math.round(change * X))
                                                    < 0
                                                || (coordX - X) > 9
                                                || (coordX - X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                Math.abs(coordX - X),
                                                ((int) Math.abs(coordY
                                                    + Math.round(change * X))));

                                            if (damage == 0)

                                                break;

                                            map[coordX - X][(int) (coordY
                                                + Math.round(change * (X)))] =
                                                '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                }

                                else // if the enemy is ahead (x)

                                {
                                    if (enmY < coordY) // if enemy is behind (y)

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordY
                                                    - Math.round(change * X))
                                                    > 9
                                                || (int) (coordY
                                                       - Math.round(change * X))
                                                    < 0
                                                || (coordX + X) > 9
                                                || (coordX + X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                Math.abs(coordX + X),
                                                ((int) Math.abs(coordY
                                                    - Math.round(change * X))));

                                            if (damage == 0)

                                                break;

                                            map[coordX + X][(int) (coordY
                                                - Math.round(change * (X)))] =
                                                '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }

                                    else

                                        for (int X = 0; X < SHORT; X++)

                                        {
                                            if ((int) (coordY
                                                    + Math.round(change * X))
                                                    > 9
                                                || (int) (coordY
                                                       + Math.round(change * X))
                                                    < 0
                                                || (coordX + X) > 9
                                                || (coordX + X) < 0)

                                                break;

                                            damage = dmgFind(damage, collision,
                                                wpnFalloff, wpnPenetration,
                                                weaponChoice,
                                                Math.abs(coordX - X),
                                                ((int) Math.abs(coordY
                                                    + Math.round(change * X))));

                                            if (damage == 0)

                                                break;

                                            map[coordX + X][(int) (coordY
                                                + Math.round(change * (X)))] =
                                                '*';

                                            if (X == SHORT - 1)

                                                eHealth -= Math.round(damage);
                                        }
                                }
                            }

                        }

                        else

                            for (int X = 0; X < LONG; X++)

                            {
                                if (axe == 'X')

                                {
                                    if (enmX < coordX)

                                    {
                                        damage = dmgFind(damage, collision,
                                            wpnFalloff, wpnPenetration,
                                            weaponChoice, Math.abs(coordX - X),
                                            Math.abs(coordY));

                                        if (damage == 0)

                                            break;

                                        map[coordX - X][coordY] = '*';

                                        if (X == LONG - 1)

                                            eHealth -= Math.round(damage);

                                    }

                                    else

                                    {
                                        damage = dmgFind(damage, collision,
                                            wpnFalloff, wpnPenetration,
                                            weaponChoice, Math.abs(coordX + X),
                                            Math.abs(coordY));

                                        if (damage == 0)

                                            break;

                                        map[coordX + X][coordY] = '*';

                                        if (X == LONG - 1)

                                            eHealth -= Math.round(damage);
                                    }

                                }

                                else

                                {
                                    if (enmY < coordY)

                                    {
                                        damage = dmgFind(damage, collision,
                                            wpnFalloff, wpnPenetration,
                                            weaponChoice, Math.abs(coordX),
                                            Math.abs(coordY - X));

                                        if (damage == 0)

                                            break;

                                        map[coordX][coordY - X] = '*';

                                        if (X == LONG - 1)

                                            eHealth -= Math.round(damage);

                                    }

                                    else

                                    {
                                        damage = dmgFind(damage, collision,
                                            wpnFalloff, wpnPenetration,
                                            weaponChoice, Math.abs(coordX),
                                            Math.abs(coordY + X));

                                        if (damage == 0)

                                            break;

                                        map[coordX][coordY + X] = '*';

                                        if (X == LONG - 1)

                                            eHealth -= Math.round(damage);
                                    }
                                }
                            }

                        break;
                    }
                }

                if (rewarded == true)

                {
                    if (coordX > 9)

                    {
                        for (int x = 0; x < 10; x++)

                        {
                            String row = input0.nextLine();

                            for (int y = 0; y < 10; y++)

                            {
                                if (row.charAt(y) != '1')

                                    map[x][y] = row.charAt(y);

                                else

                                {
                                    map[x][y] = ' ';

                                    enmX = x;

                                    enmY = y;

                                    eHealth = 100;

                                    rewarded = false;
                                }
                            }
                        }

                        mapGen(mapLog, map, collision);

                        coordX = 0;

                        BUcX = coordX;

                        BUcY = coordY;

                    }

                    else if (coordX < 0)

                    {
                        for (int x = 0; x < 10; x++)

                        {
                            String row = input0.nextLine();

                            for (int y = 0; y < 10; y++)

                            {
                                if (row.charAt(y) != '1')

                                    map[x][y] = row.charAt(y);

                                else

                                {
                                    map[x][y] = ' ';

                                    enmX = x;

                                    enmY = y;

                                    eHealth = 100;

                                    rewarded = false;
                                }
                            }
                        }

                        mapGen(mapLog, map, collision);

                        coordX = 9;

                        BUcX = coordX;

                        BUcY = coordY;
                    }

                    if (coordY > 9)

                    {
                        for (int x = 0; x < 10; x++)

                        {
                            String row = input0.nextLine();

                            for (int y = 0; y < 10; y++)

                            {
                                if (row.charAt(y) != '1')

                                    map[x][y] = row.charAt(y);

                                else

                                {
                                    map[x][y] = ' ';

                                    enmX = x;

                                    enmY = y;

                                    eHealth = 100;

                                    rewarded = false;
                                }
                            }
                        }

                        mapGen(mapLog, map, collision);

                        coordY = 0;

                        BUcX = coordX;

                        BUcY = coordY;

                    }

                    else if (coordY < 0)

                    {
                        for (int x = 0; x < 10; x++)

                        {
                            String row = input0.nextLine();

                            for (int y = 0; y < 10; y++)

                            {
                                if (row.charAt(y) != '1')

                                    map[x][y] = row.charAt(y);

                                else

                                {
                                    map[x][y] = ' ';

                                    enmX = x;

                                    enmY = y;

                                    eHealth = 100;

                                    rewarded = false;
                                }
                            }
                        }

                        mapGen(mapLog, map, collision);

                        coordY = 9;

                        BUcX = coordX;

                        BUcY = coordY;
                    }
                }

                if (collisionCheck(collision, coordX, coordY) == true)

                {
                    coordX = BUcX;

                    coordY = BUcY;
                }

                if (System.nanoTime() - AIMClock
                    >= second*0.48) // Beginning of AI movement

                {
                    if (eHealth > 0)

                    {
                        String breakdown = enemyPathFinding(map, collision,
                            BUeX, BUeY, coordX, coordY, PBClear, PSClear, enmX,
                            enmY, LlocationX, LlocationY);

                        if (breakdown.charAt(0) == 'Y')

                            PBClear = true;

                        else
                            PBClear = false;

                        if (breakdown.charAt(1) == 'Y')

                            PSClear = true;

                        else
                            PSClear = false;

                        enmX = (breakdown.charAt(2) - 48);

                        enmY = (breakdown.charAt(3) - 48);
                    }

                    AIMClock = System.nanoTime();
                }

                if (System.nanoTime() - AIBadLocationClock >= second*0.96) //TODO: i changed this from 960000000 to second*0.96. Hopefully didn't break anything

                {
                    LlocationX = enmX;

                    LlocationY = enmY;

                    AIBadLocationClock = System.nanoTime();
                }

                map[coordX][coordY] = 'X';

                if (eHealth > 0)

                    map[enmX][enmY] = 'Y';

                for (int x = 0; x < 10; x++)

                {
                    display = "";

                    for (int y = 0; y < 10; y++)

                    {
                        if (y < 9)

                            display += (map[x][y] + "  ");

                        else

                            display += (map[x][y]);
                    }

                    displayRows[x] = display;
                }

                v1.setText(displayRows[0]);

                v2.setText(displayRows[1]);

                v3.setText(displayRows[2]);

                v4.setText(displayRows[3]);

                if (pause == true)

                    v5.setText("Game paused");

                else

                    v5.setText(displayRows[4]);

                v6.setText(displayRows[5]);

                v7.setText(displayRows[6]);

                v8.setText(displayRows[7]);

                v9.setText(displayRows[8]);

                v0.setText(displayRows[9]); // Yes, this is brute force, but
                                            // whatever. god will forgive me

                if (eHealth <= 0 && rewarded == false)

                {
                    actio.munition += (int) (Math.random() * (18) + 2);

                    actio.health += (int) (Math.random() * (19) + 6);

                    rewarded = true;
                }

                newInput.setText("");

                actio.n = "";

                playMClock = System.nanoTime();
            }

        } while (true);
    }
}