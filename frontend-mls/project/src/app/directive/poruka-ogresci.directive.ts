import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appPorukaOGresci]'
})
export class PorukaOGresciDirective {
  @Input()
  appPorukaOGresciPoruka : TemplateRef<any> | undefined = undefined;


  @Input()
  set appPorukaOGresci(value:any){
    if(value != undefined && (value.length != undefined && value.length > 0)){
      this.viewContainer.clear();
      this.viewContainer.createEmbeddedView(this.templateRef);
    }else{
      this.viewContainer.clear();
      if(this.appPorukaOGresciPoruka != undefined){
        this.viewContainer.createEmbeddedView(this.appPorukaOGresciPoruka)
      }
    }
  }

  constructor(private templateRef: TemplateRef<any>, private viewContainer : ViewContainerRef) { 

  }

}
