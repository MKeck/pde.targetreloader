package de.mkeck.targetreloader;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.NullProgressMonitor;

public class TargetReloadAntTask extends Task {

    @Override
    public void execute() throws BuildException {
        TargetReloader targetReloader = new TargetReloader();
        targetReloader.reloadTarget(new NullProgressMonitor());

        super.execute();
    }

}
