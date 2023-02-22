export interface NastavniMaterijalPage<NastavniMaterijal> {
    content: NastavniMaterijal[];
  }
export interface NastavniMaterijal {
    id:number;
    autor:String;
    godinaIzdavanja:Date;
    naziv:String
}
