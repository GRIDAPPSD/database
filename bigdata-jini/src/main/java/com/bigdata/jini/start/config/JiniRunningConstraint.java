package com.bigdata.jini.start.config;

import com.bigdata.service.jini.JiniFederation;
import com.bigdata.service.zookeeper.ZookeeperFederation;
import com.bigdata.zookeeper.start.config.ServiceDependencyConstraint;

/**
 * Constraint that jini must be running (one or more service registrars must
 * have been discovered).
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public class JiniRunningConstraint extends ServiceDependencyConstraint {

    /**
     * 
     */
    private static final long serialVersionUID = 9207209964254849382L;
    
    public boolean allow(ZookeeperFederation fed) throws Exception {
    	if(fed instanceof JiniFederation)
    		return allow((JiniFederation) fed);
    	else 
    		return false;
    }

    public boolean allow(JiniFederation fed) throws Exception {

        if (fed.getDiscoveryManagement().getRegistrars().length == 0) {

            if (log.isInfoEnabled())
                log.info("No registrars have been discovered");

            return false;

        }

        // return true if any registrars have been discovered.
        return true;

    }

}