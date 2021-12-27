package com.mycompany.a4;

public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject, GameWorld gw);
}
