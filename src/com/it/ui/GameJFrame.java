package com.it.ui;
//现在有个bug,图片win之后输入a可以关闭victory显示完整图片;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements ActionListener, KeyListener {
    //规定：GameJFrame这个界面表示的就是游戏的主界面
    //以后跟游戏相关的所有逻辑都写在这个类中

    //创建一个二维数组,根据二维数据的数据进行加载
    int[][] data = new int[4][4];
    int x = 0;
    int y = 0;
    //定义一个变量让当前图片显示出来
    String path = "image\\girl\\girl11\\";

    //path最后一定要加\\  因为后面还有调用时没加,会找不到路径显示不出来
    //如果不想使用绝对路径,想使用相对路径,前面一定不要加\\  也会找不到路径而显示不出来;
//最后,如果想显示相对路径,必须在包的下一级为开始路径,如果是总包的话会找不到

    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //定义计步器
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("微信");
    JMenuItem many=new JMenuItem("帅哥美女的打赏");

    JMenuItem girlItem=new JMenuItem("美女");
    JMenuItem animalItem=new JMenuItem("动物");
    JMenuItem sportItem=new JMenuItem("运动");
    //""是text引用
    //Icon是照片路径

    public GameJFrame() {

        //初始化图形界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();
        //让界面显示出来
        this.setVisible(true);
    }

    private void initData() {
        //定义一个数组

        //这个有个小bug,在敲的时候没加0,会导致出现随机定义一个空图片,且原来的空图片也存在,就会有两个空图片
        int[] tempArr = {0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //遍历数组,得到的每一个元素;拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取随机索引
            int index = r.nextInt(tempArr.length);
            //交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //将一维数组的元素放到二维数组
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
//清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        //返回值类型：Container，表示内容面板。
        //用途： getContentPane 方法返回的内容面板是一个 Container 对象，使用它可以添加、移除和管理 Swing 组件的布局。例如，你可以通过 getContentPane().add(component) 添加组件。
        //
        //为何需要内容面板： 在 JFrame 中，内容面板是唯一允许直接放置组件的部分。JFrame 的结构层次更复杂，包含多层嵌套的面板，如根面板（root pane）、图形面板（glass pane）等。通过 getContentPane() 返回的内容面板可以集中管理和布局组件，简化窗口界面的开发过程。
        //
        //添加组件到内容面板： 默认情况下，JFrame 使用 BorderLayout 布局管理器，你可以通过 getContentPane().setLayout(new FlowLayout()); 等来修改布局。
        //
        //设置背景和样式： 由于 getContentPane() 返回的容器是 Container 类型，而 JFrame 本身不支持直接设置背景色或添加组件，所以你需要对内容面板进行自定义样式或布局设置。
        if (victory()) {
            //JLabel 是显示文本,图像,或者二者组合的组件
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
            //setText(String text)设置文本内容
            // setIcon(Icon icon)设置标签显示图片
            // setForeground(Color c)更改文本颜色
        }
        JLabel stepCount = new JLabel("步数:" + step);
        stepCount.setBounds(50, 30, 100, 20);
        //setBounds 仅在布局管理器为 null 时有效。否则布局管理器会自动调整组件位置和大小。
        this.getContentPane().add(stepCount);
        // this 代表当前类的实例，即包含这段代码的对象。通常，this 指代当前对象自身。比如，当这段代码出现在一个继承了 JFrame 的类中时，this 代表当前的 JFrame 实例。
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取当前加载的图片
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);//给图片添加边框
                //0:让图片凸起来
                //1:让图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //BevelBorder 是 Java Swing 中的一个类，用于在组件周围绘制斜面样式（凹凸效果）的边框
                this.getContentPane().add(jLabel);
            }
        }
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);//
        //把背景图片添加到界面中

        //刷新一下界面
        this.getContentPane().repaint();
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            //i : 依次表示二维数组 data里面的索引
            //data[i]：依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    //只要有一个数据不一样，则返回false
                    return false;
                }
            }
        }
        //循环结束表示数组遍历比较完毕，全都一样返回true
        return true;
    }


    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenubar = new JMenuBar();
        //创建两个选项对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu replaceJMenu =new JMenu("更换图片");
        //将每一个条目添加到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);
        aboutJMenu.add(many);

        replaceJMenu.add(animalItem);
        replaceJMenu.add(sportItem);
        replaceJMenu.add(girlItem);
        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        many.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        girlItem.addActionListener(this);

        //将菜单里面两个选项添加到菜单当中
        jMenubar.add(functionJMenu);
        jMenubar.add(aboutJMenu);
        jMenubar.add(replaceJMenu);
        //给整个页面设置菜单
        this.setJMenuBar(jMenubar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图游戏");
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        //setDefaultCloseOperation：这是JFrame类的一个方法，用于设置窗口的默认关闭操作。
        // 这个方法接受一个表示关闭操作的常量作为参数
        //JFrame.DO_NOTHING_ON_CLOSE：不执行任何操作。
        //JFrame.HIDE_ON_CLOSE：隐藏窗口，但不停止应用程序。
        //JFrame.DISPOSE_ON_CLOSE：释放窗口资源，如果没有其他窗口打开，应用程序将停止。
        //JFrame.EXIT_ON_CLOSE：退出应用程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //在大多数情况下，如果你想要使用 setBounds 来精确控制组件的位置和大小，
        // 你会先调用 setLayout(null)，因为如果不这样做，容器将使用默认的布局管理器
        // （通常是 FlowLayout），这可能会覆盖你对组件位置和大小的设置。
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//获取当前被点击的对象
        //e：这是一个事件对象的引用，它通常作为事件处理方法的参数传递进来。
        // 例如，在按钮点击事件的处理方法中，e可能代表一个ActionEvent对象，
        // 该对象包含了关于按钮点击事件的详细信息。
        Object obj = e.getSource();
        //Object是所有类的根类，因此它可以引用任何类型的对象。
        if (obj == replayItem) {
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登陆");
            this.setVisible(false);
            new com.it.ui.LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("微信");
            //创建弹框对象
            JDialog jDiaLog = new JDialog();
            //创建一个管理图片容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\weixing.jpg"));
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //将图片添加到弹框当中
            jDiaLog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDiaLog.setSize(844, 844);
            //让弹框顶置
            jDiaLog.setAlwaysOnTop(true);
            //让弹框居中
            jDiaLog.setLocationRelativeTo(null);
            //弹框不关闭则无法显示下面的操作
            jDiaLog.setModal(true);
            //让弹框显示出来
            jDiaLog.setVisible(true);
        }
        else if (obj == many) {
            System.out.println("帅哥美女的打赏");
            //创建弹框对象
            JDialog jDiaLog = new JDialog();
            //创建一个管理图片容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\many.jpg"));
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //将图片添加到弹框当中
            jDiaLog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDiaLog.setSize(844, 844);
            //让弹框顶置
            jDiaLog.setAlwaysOnTop(true);
            //让弹框居中
            jDiaLog.setLocationRelativeTo(null);
            //弹框不关闭则无法显示下面的操作
            jDiaLog.setModal(true);
            //让弹框显示出来
            jDiaLog.setVisible(true);
        }
        else if(obj==animalItem){
            Random r = new Random();
            int a=r.nextInt(1,8);
            path="C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\animal\\"+"animal"+a+"\\";
            step = 0;
            initData();
            initImage();
        }
        else if(obj==girlItem){
            Random r = new Random();
            int a=r.nextInt(1,13);
            path="C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\girl\\"+"girl"+a+"\\";
            step = 0;
            initData();
            initImage();
        }
        else if(obj==sportItem){
            Random r = new Random();
            int a=r.nextInt(1,10);
            path="C:\\Users\\火帝\\IdeaProjects\\puzzlegame\\image\\sport\\"+"sport"+a+"\\";
            step = 0;
            initData();
            initImage();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //如果想获取按键的值,可以先将他进行打印出来
        // System.out.print(code);
        if (code == 65) {
            //将所有图片全部移除
            this.getContentPane().removeAll();
            //加载第一张完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //添加背景图片
            //要先添加在上层的图片,再添加背景图片,否则会被覆盖掉,只能看到背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    //按键松开的时候会调用这个方法
    @Override
    public void keyReleased(KeyEvent e) {
//如果游戏胜利,此方法直接结束,不能再执行下面的移动代码
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 39) {
            System.out.println("向左移动");
            //对边界进行判断
            if (y == 3) {
                return;
            }
            //把空白方块向左移动,就是其他图片向右移动
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 40) {
            System.out.println("向上移动");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 37) {
            System.out.println("向右移动");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        }
        //这个很重要,如果不写按键松开搭配按键按下,就会一直停留在all的图片,无法操作,只用松开时同时调用松开代码,才会形成互补
        //按下时显示all,抬起时重新加载最新的图片
        else if (code == 65) {
            initImage();
        }
        else if (code == 87) {

            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }
}
