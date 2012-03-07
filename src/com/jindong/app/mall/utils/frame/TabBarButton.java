// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.frame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

// Referenced classes of package com.jindong.app.mall.utils.frame:
//            RadioStateDrawable

public class TabBarButton extends RadioButton
{
    public class StateController
    {

        public void addNum()
        {
            int i;
            if(num == null)
                i = 1;
            else
                i = 1 + num.intValue();
            setNum(Integer.valueOf(i));
        }

        public Integer getNum()
        {
            return num;
        }

        public void setNum(Integer integer)
        {
            num = integer;
            invalidate();
        }

        private Integer num;
        final TabBarButton this$0;

        public StateController()
        {
            this$0 = TabBarButton.this;
            super();
        }
    }


    public TabBarButton(Context context1)
    {
        super(context1);
        stateController = new StateController();
        context = context1;
    }

    public TabBarButton(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        stateController = new StateController();
        context = context1;
    }

    private void setStateImageDrawables(Drawable drawable, Drawable drawable1)
    {
        StateListDrawable statelistdrawable = new StateListDrawable();
        Drawable drawable2 = getResources().getDrawable(0x7f020069);
        int ai[] = new int[2];
        ai[0] = 0x10100a0;
        ai[1] = -0x101009d;
        statelistdrawable.addState(ai, drawable1);
        int ai1[] = new int[2];
        ai1[0] = -0x10100a0;
        ai1[1] = -0x101009d;
        statelistdrawable.addState(ai1, drawable1);
        int ai2[] = new int[2];
        ai2[0] = 0x10100a0;
        ai2[1] = 0x10100a7;
        statelistdrawable.addState(ai2, drawable);
        int ai3[] = new int[2];
        ai3[0] = -0x10100a0;
        ai3[1] = 0x10100a7;
        statelistdrawable.addState(ai3, drawable);
        int ai4[] = new int[2];
        ai4[0] = 0x10100a0;
        ai4[1] = 0x101009c;
        statelistdrawable.addState(ai4, drawable);
        int ai5[] = new int[2];
        ai5[0] = -0x10100a0;
        ai5[1] = 0x101009c;
        statelistdrawable.addState(ai5, drawable1);
        int ai6[] = new int[1];
        ai6[0] = 0x10100a0;
        statelistdrawable.addState(ai6, drawable);
        int ai7[] = new int[1];
        ai7[0] = -0x10100a0;
        statelistdrawable.addState(ai7, drawable1);
        statelistdrawable.addState(new int[0], drawable2);
        setButtonDrawable(statelistdrawable);
    }

    public StateController getStateController()
    {
        return stateController;
    }

    public void setState(String s, int i)
    {
        RadioStateDrawable radiostatedrawable = new RadioStateDrawable(context, i, s, false, 0);
        RadioStateDrawable radiostatedrawable1 = new RadioStateDrawable(context, i, s, true, 3);
        radiostatedrawable.setStateController(stateController);
        radiostatedrawable1.setStateController(stateController);
        setStateImageDrawables(radiostatedrawable1, radiostatedrawable);
    }

    public void setState(String s, int i, int j)
    {
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(j);
        setStateImageDrawables(resources.getDrawable(i), drawable);
    }

    public void setState(String s, int i, int j, int k)
    {
        RadioStateDrawable radiostatedrawable = new RadioStateDrawable(context, i, s, false, j);
        RadioStateDrawable radiostatedrawable1 = new RadioStateDrawable(context, i, s, true, k);
        radiostatedrawable.setStateController(stateController);
        radiostatedrawable1.setStateController(stateController);
        setStateImageDrawables(radiostatedrawable1, radiostatedrawable);
    }

    Context context;
    private StateController stateController;
}
