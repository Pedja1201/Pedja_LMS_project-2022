import { NastavniMaterijal } from "./nastavni-materijal";
export interface IshodNastavePage<IshodNastave> {
    content: IshodNastave[];
  }
export interface IshodNastave {
    id:number;
    opis:String;
    nastavniMaterijal:NastavniMaterijal[]
}
