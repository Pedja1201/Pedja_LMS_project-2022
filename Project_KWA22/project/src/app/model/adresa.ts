import { Mesto } from "./mesto";

export interface AdresaPage<Adresa> {
    content: Adresa[];
  }
export interface Adresa {
    id:number;
    ulica:String;
    broj:String;
    mesto:Mesto[];
}

