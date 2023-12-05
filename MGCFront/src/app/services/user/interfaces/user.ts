import {Item} from "./item";
import {Role} from "./role";

export class User {
  id:number;
  username:String;
  email:String;
  password:String;
  collection?:Item[];
  role:Role;
}
