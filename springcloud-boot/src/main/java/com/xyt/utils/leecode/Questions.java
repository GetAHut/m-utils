package com.xyt.utils.leecode;

/**
 * @Author: abby
 * @Date: 2020/12/11 9:23
 */
public interface Questions {

    /**
     * 行星爆炸问题：
     *  对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
     *  找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
     *  如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
     *      输入:
     *      asteroids = [5, 10, -5]
     *      输出: [5, 10]
     *      解释:
     *      10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids);
}
