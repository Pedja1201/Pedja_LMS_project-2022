import { Mesto } from "./mesto";

export interface AdresaData {
    content: Adresa[];
}
export interface Adresa {
    id:number;
    ulica:String;
    broj:String;
    mesto:Mesto[];
}

