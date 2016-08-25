package kankan.wheel.widget.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kankan.wheel.R;

public class DaysAdapter extends AbstractWheelTextAdapter {
    private static final int DEFAULT_DAY_COUNTS = 20;

    private Calendar mCalendar;

    /**
     * Count of days to be shown
     */
    private int mDayCount;

    public DaysAdapter(Context context, Calendar calendar, int dayCount) {
        super(context, R.layout.v4_view_wheel_day, R.id.text);

        if (dayCount < DEFAULT_DAY_COUNTS) {
            dayCount = DEFAULT_DAY_COUNTS;
        }

        mCalendar = calendar;
        mDayCount = dayCount;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        int day = -mDayCount / 2 + index;
        Calendar newCalendar = (Calendar) mCalendar.clone();
        newCalendar.roll(Calendar.DAY_OF_YEAR, day);
        Calendar todayCalendar = Calendar.getInstance();
        int todayDoY = todayCalendar.get(Calendar.DAY_OF_YEAR);
        int afterRollDoY = newCalendar.get(Calendar.DAY_OF_YEAR);

        TextView itemView = (TextView) super.getItem(index, cachedView, parent);
        if (todayDoY == afterRollDoY) {
            itemView.setText("Today");
        } else {
            itemView.setText(getItemText(index));
        }

        return itemView;
    }

    @Override
    public int getItemsCount() {
        return mDayCount + 1;
    }

    @Override
    public CharSequence getItemText(int index) {
        int day = -mDayCount / 2 + index;
        Calendar newCalendar = (Calendar) mCalendar.clone();
        newCalendar.roll(Calendar.DAY_OF_YEAR, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE MMM dd");
        return dateFormat.format(newCalendar.getTime());
    }

    /**
     * @param index in the wheel
     * @return millis since epoch
     */
    public long getItemValue(int index) {
        int day = -mDayCount / 2 + index;
        Calendar newCalendar = (Calendar) mCalendar.clone();
        newCalendar.roll(Calendar.DAY_OF_YEAR, day);
        newCalendar.get(Calendar.DAY_OF_YEAR);
        return newCalendar.getTime().getTime();
    }
}