package io.anuke.mindustry.maps.missions;

import io.anuke.mindustry.Vars;
import io.anuke.mindustry.content.blocks.StorageBlocks;
import io.anuke.mindustry.game.Team;
import io.anuke.mindustry.maps.Sector;
import io.anuke.mindustry.world.Tile;

public class BattleMission implements Mission{

    @Override
    public void generate(Tile[][] tiles, Sector sector){
        int cx = 0, cy = 0;
        outer:
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y].block() == StorageBlocks.core){
                    //set enemy core position to mirrored player core position
                    cx = tiles.length - 1 - x;
                    cy = tiles[0].length - 1 - y;
                    break outer;
                }
            }
        }

        tiles[cx][cy].setBlock(StorageBlocks.core);
        tiles[cx][cy].setTeam(Team.red);
    }

    @Override
    public boolean isComplete(){
        //TODO check all enemy teams, not just the first
        return Vars.state.teams.getTeams(false).first().cores.size == 0;
    }
}
