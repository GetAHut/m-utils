package com.xyt.utils.leecode;

import java.util.Stack;

/**
 * @Author: abby
 * @Date: 2020/12/11 9:25
 */
public class QuestionImpl implements Questions {

    /**
     * 行星爆炸
     *  思路： 使用栈 ，正数入栈，  正数永远不会发生碰撞，
     *      使用负数踢馆
     * @param asteroids
     * @return
     */
    @Override
    public int[] asteroidCollision(int[] asteroids) {

        //栈
        // peek()： 查看栈顶部的对象， 不删除
        // pop(): 删除栈顶部对象，  并返回此对象
        // push(): 将对象存入栈顶部
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()){
                    if (stack.peek() < -ast){
                        stack.pop();
                        continue;
                    }else if (stack.peek() == -ast){
                        stack.pop();
                    }
                    //跳出 collision 循环
                    break collision;
                }
                stack.push(ast);
            }
        }
        int[] ans = new int[stack.size()];
        for(int t = ans.length - 1; t >= 0 ; --t){
            ans[t] = stack.pop();
        }
        return ans;
    }
}
