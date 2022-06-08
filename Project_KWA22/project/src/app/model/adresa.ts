import { Mesto } from "./mesto";

export interface Adresa {
    id:number;
    ulica:String;
    broj:String;
    mesto:Mesto[];
    displayFn?:DisplayFn;
}

interface DisplayFn{
    (cellValue:any, data:any, key:any):any
}

