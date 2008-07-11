/**

Copyright (C) SYSTAP, LLC 2006-2007.  All rights reserved.

Contact:
     SYSTAP, LLC
     4501 Tower Road
     Greensboro, NC 27410
     licenses@bigdata.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package com.bigdata.journal;

import java.util.concurrent.ExecutorService;

import com.bigdata.btree.IIndex;
import com.bigdata.relation.IRelation;
import com.bigdata.relation.locator.IResourceLocator;
import com.bigdata.sparse.GlobalRowStoreSchema;
import com.bigdata.sparse.SparseRowStore;

/**
 * Interface accessing named indices.
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public interface IIndexStore {

    /**
     * Return a read-only view of the named index as of the specified timestamp.
     * 
     * @param name
     *            The index name.
     * @param timestamp
     *            The timestamp.
     * 
     * @return The index or <code>null</code> iff there is no index registered
     *         with that name for that timestamp.
     */
    public IIndex getIndex(String name, long timestamp);

    /**
     * Return the global {@link SparseRowStore} used to store named property
     * sets.
     * 
     * @see GlobalRowStoreSchema
     */
    public SparseRowStore getGlobalRowStore();
    
    /**
     * Return the default locator for resources that are logical index
     * containers (relations and relation containers).
     */
    public IResourceLocator getResourceLocator();

    /**
     * A {@link ExecutorService} that may be used to parallelize operations.
     * This service is automatically used when materializing located resources.
     * While the service does not impose concurrency controls, tasks run on this
     * service may submit operations to a {@link ConcurrencyManager}.
     */
    public ExecutorService getExecutorService();

    /**
     * The service that may be used to acquire exclusive or shared locks for a
     * resource hierarchy. This is used primarily when creating or destroying
     * {@link IRelation}s in order to make those operations atomic, but the
     * service may be used for any resource whether or not they correspond to
     * the global index namespace.
     */
    public IResourceLockManager getResourceLockManager();
    
}
