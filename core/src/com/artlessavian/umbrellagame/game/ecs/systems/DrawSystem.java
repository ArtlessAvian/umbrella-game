package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.AutoMap;
import com.artlessavian.umbrellagame.game.Tile;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PlayerComponent;
import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
//import com.artlessavian.umbrellagame.game.ecs.entities.RainParticle;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawSystem extends IteratingSystem
{
	private SpriteBatch batch;
	private AutoMap map;
	private Player p;
	static OrthographicCamera camera;

	private Texture cloun;
	private Texture rain;

	private Sprite tileFlyweight;

	public DrawSystem(SpriteBatch batch, AutoMap map, Player p)
	{
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
		this.map = map;
		this.p = p;
		this.camera = new OrthographicCamera(400, 225);
		this.camera.position.set(map.getStart(), 0);
//		this.camera.zoom = 5f;

		rain = new Texture("rain.png");
		cloun = new Texture("cloun.png");

		tileFlyweight = new Sprite();
	}

	@Override
	public void update(float deltaTime)
	{
//		this.getEngine().addEntity(new RainParticle(p.physicsC.pos, 0, this.getEngine()));

		camera.position.x = (camera.position.x * 49 + p.physicsC.vel.x + p.physicsC.pos.x) / 50;
		camera.position.y = p.physicsC.pos.y;


		batch.setProjectionMatrix(Maineroni.screenSpace.combined);
		for (int i = 0; i < 6; i++)
		{
			batch.draw(cloun, -(camera.position.x / 3 % 300) + i * 300 - 100, 450 - 150);
		}

		for (int i = 0; i < 100; i++)
		{
			batch.draw(rain, (float)(Math.sin(i + Gdx.graphics.getFrameId()) * 400 + 400), (float)(Math.sqrt(i) * 450/10f));
		}

		for (int i = 0; i < 4; i++)
		{
			batch.draw(cloun, -(camera.position.x / 2 % 300) + i * 300 - 100, 450 - 100);
		}

		this.camera.update();
		batch.setProjectionMatrix(this.camera.combined);


		for (int col = (int)Math.max(0, (camera.position.x - 300)/16f); col < Math.min((camera.position.x + 300)/16f, map.getWidth()); col++)
		{
			for (int row = (int)Math.max(0, (camera.position.y - 150)/16f); row < Math.min((camera.position.y + 150)/16f, map.getHeight()); row++)
			{
				if (map.get(col, row) == Tile.AIR)
				{
					continue;
				}

				tileFlyweight.setTexture(map.get(col, row).getTex());
				map.get(col, row).setUV(tileFlyweight);
				tileFlyweight.setSize(16, 16);
				tileFlyweight.setPosition(col * 16, row * 16);
				tileFlyweight.draw(batch);
			}
		}

		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
		PlayerComponent playerC = entity.getComponent(PlayerComponent.class);

		if (physicsC != null)
		{
			spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y);
		}
		if (playerC != null)
		{
			spriteC.sprite.setFlip(physicsC.facingLeft, false);
		}

		spriteC.sprite.draw(batch);
	}
}
