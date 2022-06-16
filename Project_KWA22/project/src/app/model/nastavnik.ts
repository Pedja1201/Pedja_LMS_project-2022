import { Adresa } from "./adresa";
import { Zvanje } from "./zvanje";
export interface NastavnikPage<Nastavnik> {
    content: Nastavnik[];
  }
export interface Nastavnik {
    id:number;
    email:String;
    ime:String;
    biografija:String;
    jmbg:String;
    adresa:Adresa[];
    zvanje:Zvanje[]
}
