package com.data_structure.linkedlist;

/**
 * ClassName: LinkedList
 * Description:
 * date: 2022/2/8 14:26
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LinkedList {
    public static void main(String[] args) {
        HeroesNode h1 = new HeroesNode("张翼德", "张飞");
        HeroesNode h2 = new HeroesNode("刘备", "刘玄德");
        HeroesNode h3 = new HeroesNode("关羽", "关云长");
        HeroesNode h4 = new HeroesNode("诸葛亮", "孔明");
        HeroesNode h5 = new HeroesNode("庞统", "凤雏");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 添加节点
        singleLinkedList.init(h3); // 载入表头
        singleLinkedList.add(h5); // 向链表后方插入数据
        singleLinkedList.add(h2); // 向链表后方插入数据
        singleLinkedList.add(h2); // 向链表后方插入数据
        singleLinkedList.add(h4); // 向链表后方插入数据
        singleLinkedList.add(h1);

        // 展示链表中的数据
        singleLinkedList.list();

        // 更新节点
        singleLinkedList.updateNode(5,"士元");

        // 删除节点
        singleLinkedList.removeNode(3);
        singleLinkedList.list();
    }


    // 单向链表类
    static class SingleLinkedList {
        private HeroesNode head = new HeroesNode();


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

        // 使表头指向一个链表
        public void init(HeroesNode hero) {
            head.next = hero;
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
                    '}';
        }
    }
}
