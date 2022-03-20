package com.data_structure.linkedlist;

/**
 * ClassName: CirCleLinkedList
 * Description: 子类构建一个环形链表，以解决约瑟夫环(Joseph)问题
 * date: 2022/2/9 15:49
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class CirCleLinkedList {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10); // 添加10个节点
        circleSingleLinkedList.list(); // 列出环形列表所有的节点
        circleSingleLinkedList.countBoy();
    }

    static class CircleSingleLinkedList {
        private Boy head = new Boy();
        private int length;

        // 创建环形
        public void addBoy(int nums) {
            length = nums;
            if (nums < 1) {
                System.out.println("人数错误，请重新设置");
            }
            Boy cur = head;

            for (int i = 1; i <= nums; i++) {
                Boy friend = new Boy(i, MyUtil.getRandomName());
                cur.next = friend;
                if (i == nums) { // 生成的最后一个节点，形成一个环形
                    friend.next = head.next;
                }
                cur = friend;  // 记录上一个节点的地址

            }
        }


        // 遍历环形链表
        public void list() {
            if (head.next == null) {
                System.out.println("当前链表没有数据");
                return;
            }
            Boy tmp = head.next; // 指向环形链表第一个节点
            while (true) {
                // 先打印节点 在判断该节点是否指向头节点，如果指向则停止遍历
                System.out.println(tmp);
                if (tmp.next == head.next) {
                    return;
                }
                tmp = tmp.next;
            }
        }

        // 解决约瑟夫环问题
        public void countBoy() {
            System.out.print("JosephCircle:");
            while (length > 0) {
                // 随机出局 编号
                int index = ((int) (Math.random() * length)) + 1;

                // 设置前后指针
                Boy cur = head.next; // 指向第一个
                Boy pre = head.next;
                while (true) {
                    pre = pre.getNext(); // 移动到环形链表末尾
                    if (pre.next == head.next) {
                        break;
                    }
                }

                for (int i = 1; i <= length; i++) {
                    if (index == 1) {
                        // 出局的是第一个
                        System.out.print(cur.getId() + "\t");
                        head.next = cur.next; // 将第二个当作表头
                        pre.setNext(cur.next); // 最后一个连接第二个
                        cur.next = null; //  第一个置空 , 等待被垃圾回收
                        break;

                    } else if (i == index) {
                        // 出局的是中点的一个或最后一个(逻辑一致)
                        System.out.print(cur.getId() + "\t");
                        pre.next = cur.next;
                        cur.next = null;
                        break;
                    }

                    // 没有出局 指针跟随移动
                    cur =cur.getNext();
                    pre = pre.getNext();
                }
                length--; // 出局一个length减一个
            }
        }
    }

    static class Boy {
        private int id;
        private String name;
        private Boy next;

        public int getId() {
            return id;
        }

        public Boy(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }

        public Boy() {
        }

        @Override
        public String toString() {
            return "Boy{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
