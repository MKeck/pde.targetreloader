package de.mkeck.targetreloader;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.internal.core.PDECore;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
public class TargetReloader {

    public IStatus reloadTarget(final IProgressMonitor monitor) {
		ITargetPlatformService service = (ITargetPlatformService) PDECore.getDefault()
                                                                         .acquireService(ITargetPlatformService.class.getName());
        
        Bundle bundle = FrameworkUtil.getBundle(getClass());

        try {
            ITargetHandle workspaceTargetHandle = service.getWorkspaceTargetHandle();
            ITargetDefinition targetDefinition = workspaceTargetHandle.getTargetDefinition();

            targetDefinition.resolve(monitor);
            LoadTargetDefinitionJob.load(targetDefinition);
        }
        catch (Exception e) {
            e.printStackTrace();
            ILog log = Platform.getLog(bundle);
            log.log(new Status(IStatus.ERROR, bundle.getSymbolicName(), "Could not reload target", e));
        }

        Status status = new Status(IStatus.OK, bundle.getSymbolicName(), "Target reload was reloaded"); //$NON-NLS-1$
        return status;
    }

}
