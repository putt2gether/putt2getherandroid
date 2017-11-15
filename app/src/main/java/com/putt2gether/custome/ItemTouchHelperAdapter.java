package com.putt2gether.custome;

/**
 * Created by Ajay on 29/07/2016.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}