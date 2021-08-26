package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class AskSomeoneForHelp implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String Question = (String) delegateExecution.getVariable("question");

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("AskSomeoneForHelp")
                .setVariable("question",Question)
                .correlate();
    }
}
