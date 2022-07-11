package com.robot.qq.util;

import java.util.HashMap;
import java.util.Random;

/**
 * 苏联笑话工具类
 *
 * @author wangzk
 */
public class JokeUtils {


    /**
     * 获取苏联笑话
     *
     * @param str String
     * @return String
     */
    public static String getJoke(String str) {
        Random random = new Random();
        String num = String.valueOf(random.nextInt(16));
        String name = StringUseUtils.getListStr(str, "：").get(1);
        return jokeList(name, num);
    }

    /**
     * 苏联笑话列表
     *
     * @param name String
     * @param num  String
     * @return String
     */
    private static String jokeList(String name, String num) {

        String joke0 = "精神病院里播放着" + name + "的演讲。讲完一段，所有人都热烈鼓掌，只有一个人站着不动。 旁人问道：" +

                "“你为什么不鼓掌？” " +

                "答：“因为我是医生。”";
        String joke1 = "一老者在人行道散步，不慎落入道旁河中，高呼救命" +
                "    两警察闻之，视若不见，仍边走边谈笑如故" +
                "    老者情急生智，遂高呼“打倒" + name + "”！两警察闻之大惊，急跳入河中，将老者拖上岸来铐之";
        String joke2 = "昨天莫斯科发生了地震，但学者们对此表示怀疑，因为莫斯科处在非地震带上。最后经过研究，并非是地震，而是" + name + "佩有勋章的衣服掉在了地上 ";

        String joke3 = "那些别有用心的人是怎样黑" + name + "的？”" +
                "“他们把" + name + "说的内容原文复述了。”";

        String joke4 = "一个莫斯科市民的鹦鹉丢了。这是只会骂人的鹦鹉，要是落到" + name + "的手里可糟了。这人便在报纸 上发表了一篇声明：“本人遗失鹦鹉一只。另，本人不同意它的政治观点。”";

        String joke5 = "一天" + name + "因私外出，嫌司机车开的太慢，催促了好几次。但因交通拥挤，还是不能让他满意。最后" + name + "一把抢过方向盘，把司机推到后面，自己开起来。他一路横冲直撞，造成一片混乱。有人打电话向交通局长反映。 " +

                "局长大怒，质问该地段交警。 " +

                "局长：“看到肇事者没有？” " +

                "警察：“看到了。” " +

                "局长：“为什么不逮捕他？” " +

                "警察：“我不敢？” " +

                "局长：“为什么？” " +

                "警察：“他的官很大。” " +

                "局长：“有多大？” " +

                "警察：“不知道，反正他的司机是" + name + "。";

        String joke6 = name + "视察农场，看到猪儿乖乖，一时兴起站在猪中间照了张像。 " +

                "待到报纸准备发表时，编辑为照片的标题犯了难—— " +

                "“" + name + "和猪在一起”，不好；" +

                " “猪和" + name + "在一起”，也不好； " +

                "…… " +

                "报纸出版后，照片下的说明文字是—— " +

                "“左起第三位是" + name + "。”";

        String joke7 = name + "的汽车被一头牛挡住了，怎么也赶不走，" + name + "便下车对牛说：“你再不走，我就把你送到集体农庄去。”牛听了便一溜烟的跑开了。";

        String joke8 = "一间牢房里关了三个人，彼此间谈起坐牢的原因。" +
                "第一个人说：因为我支持" + name + "；" +
                "第二个人说：因为我反对" + name + "；" +
                "第三个人说：我就是" + name + "。 ";

        String joke9 = name + "在揭露群主的暴行时，台下有人递条子上去。" + name + "当场宣读了条子的内容：“当时你在干什么？”。" +
                "然后厉声喝道：“这是谁写的，请站出来！” " +
                "连问三次，台下一直没有人站出来。 " + name + "于是说道：“现在让我来回答你吧，当时我就坐在你的位置上。”";

        String joke10 = "集体农庄庄员伊万在河里捉到一条大鱼，高兴的回到家里和老婆说" +
                "“看，我们有炸鱼吃了！” " +
                "“没有油啊。” " +
                "“那就煮！” " +
                "“没锅。” " +
                "“烤鱼！” " +
                "“没柴。” " +
                "伊万气死了，走到河边把鱼扔了回去。那鱼在水里划了一个半圆，上身出水，举起右鳍激动地高呼： “" + name + "万岁！";

        String joke11 = "战争时期，河内物资奇缺，向" + name + "求救。"
                + name + "电 ：勒紧腰带 。 河内回电 ：请给腰带 。";

        String joke12 = name + "：“同志们，美国人登上了月球，我们不能再等了，党决定让你们上太阳。 ” " +
                "宇航员：“总书记同志，我们会被烧死的。 ” " +

                name + "：“没关系，同志们，我们都替你们想好了，你们晚上去。 ”";

        String joke13 = "莫斯科地铁上：" +

                "“请问您是为" + name + "工作的同志吗？？” " +

                "“不是。” " +

                "“那您有没有亲戚或朋友为" + name + "工作呢？”" +

                " “没有。” " +

                "“那您是否跟" + name + "有些交往或联系？” " +

                "“没有，你要干嘛啊？” " +

                "“干嘛，他妈的，你踩着我的脚了！” ";

        String joke14 = name + "在冬季奥运会上阅读讲话稿：" +

                " “O O O O O 。” " +

                "“不，”，" +

                "他的助理向他低声道，" +

                "“那是奥运会的图标。”";

        String joke15 = "莫斯科卢比扬卡9号大楼外。一个愁眉苦脸的男子一边走路一边自言自语：" +

                "“肥皂没有，电池没有，袜子也没有……” " +

                "这时旁边走过来一个看起来象是便衣的人小声对他说：" +

                "“公民同志，您要是再这样诋毁我们伟大的" + name + "，我就要拿手枪把敲你的脑袋了！”那个男子看看便衣警察，继续自言自语：" +

                "“看看，连子弹也没有……”";

        HashMap<String, String> map = new HashMap<>();

        map.put("0", joke0);
        map.put("1", joke1);
        map.put("2", joke2);
        map.put("3", joke3);
        map.put("4", joke4);
        map.put("5", joke5);
        map.put("6", joke6);
        map.put("7", joke7);
        map.put("8", joke8);
        map.put("9", joke9);
        map.put("10", joke10);
        map.put("11", joke11);
        map.put("12", joke12);
        map.put("13", joke13);
        map.put("14", joke14);
        map.put("15", joke15);

        return map.get(num);

    }


}
