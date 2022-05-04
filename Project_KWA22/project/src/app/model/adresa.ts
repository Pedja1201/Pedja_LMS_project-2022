import { Mesto } from "./mesto";

export interface Adresa {
    id:number;
    ulica:String;
    broj:String;
    mesto:Mesto[]
}
