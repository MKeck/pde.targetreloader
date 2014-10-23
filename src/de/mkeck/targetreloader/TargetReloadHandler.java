package de.mkeck.targetreloader;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

public class TargetReloadHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        Job reloadTargetJob = new Job("Reloading Target") { //$NON-NLS-1$

            @Override
            protected IStatus run(final IProgressMonitor monitor) {
                TargetReloader targetReloader = new TargetReloader();
                IStatus status = targetReloader.reloadTarget(monitor);
                return status;
            }
        };

        reloadTargetJob.schedule();

        return null;
    }

}
