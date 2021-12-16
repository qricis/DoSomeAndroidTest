package com.android.myfragmenttest;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/1/29 14:57
 * @version 1.0.0
 */
public interface MyFragmentListener {
    /**
     * 任意给activity传递参数
     * @param str 传递的参数
     */
    void whatFragmentSay(String str);

    /**
     * 替换右边的fragment
     */
    void changeRightFragment();
}
