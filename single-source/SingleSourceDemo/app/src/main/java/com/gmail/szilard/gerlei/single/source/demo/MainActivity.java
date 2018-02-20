package com.gmail.szilard.gerlei.single.source.demo;

import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static com.gmail.szilard.gerlei.single.source.demo.Utils.ONE_THOUSAND;
import static com.gmail.szilard.gerlei.single.source.demo.Utils.TEN_MILLION;
import static com.gmail.szilard.gerlei.single.source.demo.Utils.generateRandomFloatArray;

public class MainActivity extends AppCompatActivity {

    private RenderScript rs;
    private ScriptC_singlesource script;
    private Allocation inputAllocation;
    private Allocation outputAllocation;
    // private Element myCustomElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRenderScript();
    }

    private void initRenderScript() {
        rs = RenderScript.create(this);

        inputAllocation = Allocation.createSized(
                rs, Element.F32(rs), TEN_MILLION);
        outputAllocation = Allocation.createSized(
                rs, inputAllocation.getElement(), TEN_MILLION);

        script = new ScriptC_singlesource(rs);
    }

    public void onStartButtonClick(View view) {
        float[] randomArr = generateRandomFloatArray(1, 100, TEN_MILLION);
        float[] resultArr = new float[randomArr.length];
        inputAllocation.copyFrom(randomArr);


        long startNanos = System.nanoTime();
        script.invoke_process(inputAllocation, outputAllocation);
        rs.finish();
        long finishNanos = System.nanoTime();
        long elapsedTime = finishNanos - startNanos;
        Log.i(this.getClass().getName(), "Elapsed time: " + elapsedTime);


        outputAllocation.copyTo(resultArr);

        //   printArray(MainActivity.class, "SINGLESOURCE RS RESULT", resultArr);
    }


}
