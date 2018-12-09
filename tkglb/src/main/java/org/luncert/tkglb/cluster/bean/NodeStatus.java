package org.luncert.tkglb.cluster.bean;

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