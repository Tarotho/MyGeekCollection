import {User} from "./user";
import {Videogame} from "../../videogame/interfaces/videogame";

export class Item {
  id:number;
  user:User;
  videogame:Videogame;
  generalCondition:String;
  box:Boolean;
  manual:Boolean;
}
