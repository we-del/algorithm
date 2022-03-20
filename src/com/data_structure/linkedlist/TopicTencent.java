package com.data_structure.linkedlist;

import java.util.Stack;

/**
 * ClassName: TopicTencent
 * Description:
 * date: 2022/2/8 16:44
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TopicTencent {
    public static void main(String[] args) {
        HeroesNode h1 = new HeroesNode("张翼德", "张飞");
        HeroesNode h2 = new HeroesNode("刘备", "刘玄德");
        HeroesNode h3 = new HeroesNode("关羽", "关云长");
        HeroesNode h4 = new HeroesNode("诸葛亮", "孔明");
        HeroesNode h5 = new HeroesNode("庞统", "凤雏");
        HeroesNode x1 = new HeroesNode("张翼德", "张飞");
        HeroesNode x2 = new HeroesNode("孙悟空", "悟空");
        HeroesNode x3 = new HeroesNode("沙悟净", "沙僧");
        HeroesNode x4 = new HeroesNode("猪八戒", "八戒");
        HeroesNode x5 = new HeroesNode("唐玄奘", "唐僧");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.init(x5);
        singleLinkedList2.add(x3);
        singleLinkedList2.add(x1);
        singleLinkedList2.add(x4);
        singleLinkedList2.add(x2);
//        // 添加节点
        singleLinkedList.init(h3); // 载入表头
        singleLinkedList.add(h5); // 向链表后方插入数据
        singleLinkedList.add(h2); // 向链表后方插入数据
        singleLinkedList.add(h4); // 向链表后方插入数据
        singleLinkedList.add(h1);

        System.out.println("======合并前");
        singleLinkedList.list();
        System.out.println("head1==========");
        System.out.println(singleLinkedList.getHead().next);
        singleLinkedList2.list();

        System.out.println("head2==========");
        System.out.println(singleLinkedList2.getHead().next);
        System.out.println("=========合并后");
        HeroesNode heroesNode = singleLinkedList.mergeLinkedList(singleLinkedList.getHead(), singleLinkedList2.getHead());
        while(heroesNode.next !=null){
            heroesNode = heroesNode.next;
            System.out.println(heroesNode);
        }
        // 展示链表中的数据=

        // 更新节点
        //singleLinkedList.updateNode(5, "士元");

        // 删除节点
        //singleLinkedList.removeNode(3);
        //System.out.println("================111111111111");
        //singleLinkedList.list();
        //System.out.println("当前链表个数:" + singleLinkedList.count());
        // singleLinkedList.findReverseNum(1);


//        singleLinkedList.list();
//
//        System.out.println("222222222222================");
//        singleLinkedList.reverseLinkedList();
//        singleLinkedList.list();
//        System.out.println("===============");
//        singleLinkedList.reversePrint();
    }


    // 单向链表类
    static class SingleLinkedList {
        private HeroesNode head = new HeroesNode();

        public HeroesNode getHead() {
            return head;
        }

        public void setHead(HeroesNode head) {
            this.head = head;
        }

        // 向链表尾部添加数据(根据节点id排序)
        public void add(HeroesNode hero) {
            if (head.next == null) {
                System.out.println("链表中还没有数据");
                return;
            }

            // 查找此节点是否存在
            if (find(hero)) {
                System.out.println("链表中以存在此节点");
                return;
            }

            // 获得前后节点
            HeroesNode tmp = head.next;
            HeroesNode pre = head;

            while (true) {
                // id插入
                if (tmp.node_id > hero.node_id) {

                    // 如果添加节点比头部小 则替换头部节点
                    if (head.next.node_id == tmp.node_id) {
                        head.next = hero;
                    } else {
                        // 如果添加节点比中部节点小 则插入添加节点到中部节点前
                        pre.next = hero;
                    }
                    hero.next = tmp;
                    break;
                }


                // 尾部插入(id最大尾部插入)
                if (tmp.next == null) {
                    tmp.next = hero;
                    break;
                }
                tmp = tmp.next;
                pre = pre.next;
            }
        }

        // 计算链表个数
        public Integer count() {
            if (head.next == null) {
                return null;
            }
            int count = 0;
            HeroesNode tmp = head.next;
            while (true) {
                count++;
                if (tmp.next == null) {
                    return count;
                }
                tmp = tmp.next;
            }
        }


        // 查找链表中是否存在此节点
        public boolean find(HeroesNode hero) {

            // 如果链表中有相同数据则放弃存储
            HeroesNode tmp = head.next;
            while (true) {
                if (tmp.node_id == hero.node_id) {
                    return true;
                }
                if (tmp.next == null) { // 遍历完都没有相同的节点
                    return false;
                }
                tmp = tmp.next;
            }

        }

        // 更新节点内容
        public void updateNode(int id, String content) {

            HeroesNode tmp = head.next;
            while (true) {
                if (tmp.node_id == id) {
                    tmp.nickName = content;
                    System.out.println("完成节点更新");
                    return;
                }
                if (tmp.next == null) {
                    System.out.println("没有改节点");
                    return;
                }
                tmp = tmp.next;
            }
        }

        // 删除节点
        public void removeNode(int id) {
            HeroesNode pre = head;
            HeroesNode tmp = head.next;
            while (true) {

                // 找到了节点
                if (tmp.node_id == id) {
                    pre.next = tmp.next;
                    tmp.next = null;
                    System.out.println("节点删除成功");
                    return;
                }

                // 没有找到节点
                if (tmp.next == null) {
                    System.out.println("该节点不存在");
                    return;
                }

                // 一直往后移动
                pre = pre.next;
                tmp = tmp.next;
            }
        }

        // 题：查找单链表中倒数第k个节点
        public void findReverseNum(int k) {
            if (k > count()) {
                System.out.println("超出了节点范围");
                return;
            }
            if (k < 0) {
                System.out.println("节点不能为负数");
                return;
            }
            int index = count() - k + 1; // 为倒数的整数索引 如  k=1(倒数第一个)
            int length = 0;
            HeroesNode tmp = head.next;
            while (true) {
                length++;
                if (length == index) {
                    System.out.println("倒数第" + k + "个的链表为:" + tmp);
                    break;
                }
                if (tmp.next == null) {
                    break;
                }
                tmp = tmp.next;
            }

        }

        // 遍历链表的数据
        public void list() {
            if (head.next == null) {
                System.out.println("此链表没有数据");
                return;
            }
            HeroesNode tmp = head.next;
            while (true) {
                if (tmp.next == null) {
                    System.out.println(tmp);
                    break;
                }
                System.out.println(tmp);
                tmp = tmp.next;
            }
        }

        // 链表反转
        public void reverseLinkedList() {
            // 判断head是否为空或链表是否只有一个节点
            if (head.next == null || head.next.next == null) {
                return;
            }
            HeroesNode reverseHead = new HeroesNode(); // 反转头节点
            HeroesNode cur = head.next; // 指向第一个头节点
            HeroesNode next = null; // 辅助移动

            while (cur != null) {
                next = cur.next;
                cur.next = reverseHead.next; // 让最新的节点指向头节点
                reverseHead.next = cur; // 让最新的节点变为头节点
                cur = next; // 移动到下一个节点
            }
            head.next = reverseHead.next; //  改变head头节点指向

        }

        // 逆序打印链表
        public void reversePrint() {
            if (head.next == null) {
                System.out.println("这只一个空链表");
                return;
            }
            Stack<HeroesNode> heroesNodes = new Stack<>();
            HeroesNode cur = head.next;
            while (cur != null) {
                heroesNodes.add(cur);
                cur = cur.next;
            }
            while (heroesNodes.size() > 0) {
                System.out.println(heroesNodes.pop());
            }

        }

        // 使表头指向一个链表
        public void init(HeroesNode hero) {
            head.next = hero;
        }

        // 合并链表
        public HeroesNode mergeLinkedList(HeroesNode h1, HeroesNode h2) {
            if (h1.next == null || h2.next == null) {
                System.out.println("传入了空的链表");
                return null;
            }
            HeroesNode tmp1 = h1.next;
            HeroesNode tmp2 = h2.next;
            HeroesNode tmpLinkedList = new HeroesNode();
            HeroesNode pre = null;

            while (true) {
                if (tmp1 == null) {
                    while (true) {
                        if (tmp2 == null) {
                            return tmpLinkedList;
                        }
                        pre.next = tmp2;
                        pre = tmp2;
                        tmp2 = tmp2.next;
                    }
                }
                if (tmp2 == null) {
                    while (true) {
                        if (tmp1 == null) {
                            return tmpLinkedList;
                        }
                        pre.next = tmp1;
                        pre = tmp1;
                        tmp1 = tmp1.next;
                    }
                }

                    if (tmp1.node_id < tmp2.node_id) {
                        // tmp1存入 并后移
                        if (tmpLinkedList.next == null) {
                            tmpLinkedList.next = tmp1;
                        } else {
                            pre.next = tmp1;
                        }
                        pre = tmp1;
                        tmp1 = tmp1.next;

                    } else {
                        // tmp2存入 并后移
                        if (tmpLinkedList.next == null) {
                            tmpLinkedList.next = tmp2;
                        } else {
                            pre.next = tmp2;
                        }
                        pre = tmp2;

                        tmp2 = tmp2.next;
                    }

            }
        }
    }

    static class HeroesNode {
        static int num = 1;
        private int node_id = num;
        private String name;
        private String nickName;
        private HeroesNode next;

        {
            num++;
        }

        public HeroesNode(int node_id, String name, String nickName) {
            this.node_id = node_id;
            this.name = name;
            this.nickName = nickName;
        }

        public HeroesNode(String nickName, String name) {
            this.nickName = nickName;
            this.name = name;
        }

        public HeroesNode() {
        }

        @Override
        public String toString() {
            return "HeroesNode{" +
                    "node_id=" + node_id +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName +
                    "\'}";
        }
    }
}
