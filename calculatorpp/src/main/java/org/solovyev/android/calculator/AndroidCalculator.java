package org.solovyev.android.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import jscl.NumeralBase;
import jscl.math.Generic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.solovyev.android.calculator.history.CalculatorHistoryState;
import org.solovyev.android.calculator.jscl.JsclOperation;
import org.solovyev.common.history.HistoryAction;

import java.util.List;

/**
 * User: serso
 * Date: 9/22/12
 * Time: 5:42 PM
 */
public class AndroidCalculator implements Calculator {

    @NotNull
    private final Calculator calculator = new CalculatorImpl();

    public static void showEvaluationError(@NotNull Context context, @NotNull final String errorMessage) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final View errorMessageView = layoutInflater.inflate(R.layout.display_error_message, null);
        ((TextView) errorMessageView.findViewById(R.id.error_message_text_view)).setText(errorMessage);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setPositiveButton(R.string.c_cancel, null)
                .setView(errorMessageView);

        builder.create().show();
    }

    public void init(@NotNull final Activity activity) {
        setEditor(activity);
        setDisplay(activity);
    }

    public void setDisplay(@NotNull Activity activity) {
        final AndroidCalculatorDisplayView displayView = (AndroidCalculatorDisplayView) activity.findViewById(R.id.calculatorDisplay);
        setDisplay(activity, displayView);
    }

    public void setDisplay(@NotNull Context context, @NotNull AndroidCalculatorDisplayView displayView) {
        displayView.init(context);
        CalculatorLocatorImpl.getInstance().getDisplay().setView(displayView);
    }

    public void setEditor(@NotNull Activity activity) {
        final AndroidCalculatorEditorView editorView = (AndroidCalculatorEditorView) activity.findViewById(R.id.calculatorEditor);
        setEditor(activity, editorView);
    }

    public void setEditor(@NotNull Context context, @NotNull AndroidCalculatorEditorView editorView) {
        editorView.init(context);
        CalculatorLocatorImpl.getInstance().getEditor().setView(editorView);
    }


    /*
    **********************************************************************
    *
    *                           DELEGATED TO CALCULATOR
    *
    **********************************************************************
    */

    @Override
    @NotNull
    public CalculatorEventData evaluate(@NotNull JsclOperation operation, @NotNull String expression) {
        return calculator.evaluate(operation, expression);
    }

    @Override
    @NotNull
    public CalculatorEventData evaluate(@NotNull JsclOperation operation, @NotNull String expression, @NotNull Long sequenceId) {
        return calculator.evaluate(operation, expression, sequenceId);
    }

    @Override
    public boolean isConversionPossible(@NotNull Generic generic, @NotNull NumeralBase from, @NotNull NumeralBase to) {
        return calculator.isConversionPossible(generic, from, to);
    }

    @Override
    @NotNull
    public CalculatorEventData convert(@NotNull Generic generic, @NotNull NumeralBase to) {
        return calculator.convert(generic, to);
    }

    @Override
    @NotNull
    public CalculatorEventData fireCalculatorEvent(@NotNull CalculatorEventType calculatorEventType, @Nullable Object data) {
        return calculator.fireCalculatorEvent(calculatorEventType, data);
    }

    @Override
    @NotNull
    public CalculatorEventData fireCalculatorEvent(@NotNull CalculatorEventType calculatorEventType, @Nullable Object data, @NotNull Long sequenceId) {
        return calculator.fireCalculatorEvent(calculatorEventType, data, sequenceId);
    }

    @Override
    public void init() {
        this.calculator.init();
    }

    @Override
    public void addCalculatorEventListener(@NotNull CalculatorEventListener calculatorEventListener) {
        calculator.addCalculatorEventListener(calculatorEventListener);
    }

    @Override
    public void removeCalculatorEventListener(@NotNull CalculatorEventListener calculatorEventListener) {
        calculator.removeCalculatorEventListener(calculatorEventListener);
    }

    @Override
    public void fireCalculatorEvent(@NotNull CalculatorEventData calculatorEventData, @NotNull CalculatorEventType calculatorEventType, @Nullable Object data) {
        calculator.fireCalculatorEvent(calculatorEventData, calculatorEventType, data);
    }

    @Override
    public void fireCalculatorEvents(@NotNull List<CalculatorEvent> calculatorEvents) {
        calculator.fireCalculatorEvents(calculatorEvents);
    }

    @Override
    public void doHistoryAction(@NotNull HistoryAction historyAction) {
        calculator.doHistoryAction(historyAction);
    }

    @Override
    public void setCurrentHistoryState(@NotNull CalculatorHistoryState editorHistoryState) {
        calculator.setCurrentHistoryState(editorHistoryState);
    }

    @Override
    @NotNull
    public CalculatorHistoryState getCurrentHistoryState() {
        return calculator.getCurrentHistoryState();
    }

    @Override
    public void evaluate() {
        calculator.evaluate();
    }

    @Override
    public void evaluate(@NotNull Long sequenceId) {
        calculator.evaluate(sequenceId);
    }

    @Override
    public void simplify() {
        calculator.simplify();
    }

}