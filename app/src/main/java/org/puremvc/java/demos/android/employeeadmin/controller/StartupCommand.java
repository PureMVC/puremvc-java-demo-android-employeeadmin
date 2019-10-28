package org.puremvc.java.demos.android.employeeadmin.controller;

import org.puremvc.java.multicore.patterns.command.MacroCommand;

public class StartupCommand extends MacroCommand {

    @Override
    protected void initializeMacroCommand() {
        addSubCommand(() -> new PrepModelCommand());
        addSubCommand(() -> new PrepViewCommand());
    }

}
