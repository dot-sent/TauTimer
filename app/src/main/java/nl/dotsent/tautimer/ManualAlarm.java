package nl.dotsent.tautimer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmSetter} interface
 * to handle interaction events.
 * Use the {@link ManualAlarm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManualAlarm extends Fragment {

    private AlarmSetter mSetter;
    private EditText etUnits;
    private EditText etSegments;
    private EditText etDays;

    public ManualAlarm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManualAlarm.
     */
    // TODO: Rename and change types and number of parameters
    public static ManualAlarm newInstance() {
        ManualAlarm fragment = new ManualAlarm();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manual_alarm, container, false);
        etDays = v.findViewById(R.id.etDays);
        etUnits = v.findViewById(R.id.etUnits);
        etSegments = v.findViewById(R.id.etSegments);
        return v;
    }

    public void onButtonPressed(View v) {
        //((Button)v).setText("Testing functionality");
        if (mSetter != null) {
            mSetter.setAlarm(Integer.parseInt(etDays.getText().toString()) * 100000 +
                    Integer.parseInt(etSegments.getText().toString()) * 1000 +
                    Integer.parseInt(etUnits.getText().toString()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AlarmSetter) {
            mSetter = (AlarmSetter) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AlarmSetter");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSetter = null;
    }
}
