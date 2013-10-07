/*
 * FeedInformation.java
 *
 * Created on August 1, 2005, 7:11 PM
 *
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.rometools.feed.module.itunes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sun.syndication.feed.CopyFrom;

/**
 * This class contains information for iTunes podcast feeds that exist at the Channel level.
 * 
 * @version $Revision: 1.2 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class FeedInformationImpl extends AbstractITunesObject implements FeedInformation {
    private String ownerName;
    private String ownerEmailAddress;
    private URL image;
    private List categories;

    /**
     * Creates a new instance of FeedInformationImpl
     */
    public FeedInformationImpl() {
    }

    /**
     * The parent categories for this feed
     * 
     * @return The parent categories for this feed
     */
    public List getCategories() {
        return categories == null ? (categories = new ArrayList()) : categories;
    }

    /**
     * The parent categories for this feed
     * 
     * @param categories The parent categories for this feed
     */
    public void setCategories(final List categories) {
        this.categories = categories;
    }

    /**
     * Returns the owner name for the feed
     * 
     * @return Returns the owner name for the feed
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the owner name for the feed
     * 
     * @param ownerName Sets the owner name for the feed
     */
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the owner email address for the feed.
     * 
     * @return Returns the owner email address for the feed.
     */
    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    /**
     * Sets the owner email address for the feed.
     * 
     * @param ownerEmailAddress Sets the owner email address for the feed.
     */
    public void setOwnerEmailAddress(final String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    /**
     * Returns the URL for the image.
     * 
     * NOTE: To specification images should be in PNG or JPEG format.
     * 
     * @return Returns the URL for the image.
     */
    public URL getImage() {
        return image;
    }

    /**
     * Sets the URL for the image.
     * 
     * NOTE: To specification images should be in PNG or JPEG format.
     * 
     * @param image Sets the URL for the image.
     */
    public void setImage(final URL image) {
        this.image = image;
    }

    /**
     * Required by the ROME API
     * 
     * @param obj object to copy property values from
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final FeedInformationImpl info = (FeedInformationImpl) obj;
        setAuthor(info.getAuthor());
        setBlock(info.getBlock());

        getCategories().clear();
        if (info.getCategories() != null) {
            getCategories().addAll(info.getCategories());
        }

        setExplicit(info.getExplicit());

        try {
            if (info.getImage() != null) {
                setImage(new URL(info.getImage().toExternalForm()));
            }
        } catch (final MalformedURLException e) {
            Logger.getAnonymousLogger().fine("Error copying URL:" + info.getImage());
        }

        if (info.getKeywords() != null) {
            setKeywords(info.getKeywords().clone());
        }

        setOwnerEmailAddress(info.getOwnerEmailAddress());
        setOwnerName(info.getOwnerName());
        setSubtitle(info.getSubtitle());
        setSummary(info.getSummary());
    }

    /**
     * Returns a copy of this FeedInformationImpl object
     * 
     * @return Returns a copy of this FeedInformationImpl object
     */
    @Override
    public Object clone() {
        final FeedInformationImpl info = new FeedInformationImpl();
        info.copyFrom(this);

        return info;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        sb.append(" email: ");
        sb.append(getOwnerEmailAddress());
        sb.append(" name: ");
        sb.append(getOwnerName());
        sb.append(" image: ");
        sb.append(getImage());
        sb.append(" categories: ");
        sb.append(getCategories());
        sb.append("]");
        sb.append(super.toString());

        return sb.toString();
    }
}