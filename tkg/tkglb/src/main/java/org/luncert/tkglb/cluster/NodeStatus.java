package org.luncert.tkglb.cluster;

/**
 * Ready -> Reading
 * Ready -> Updating
 * Ready -> Invaliable
 * Reading -> Ready
 * Reading -> Invaliable
 * Updating -> Ready
 * Updating -> Invaliable
 */
public enum NodeStatus {

    Ready, Reading, Updating, Invaliable

}