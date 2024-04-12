import { Injectable } from '@angular/core';



export class Message{
  content: String;
  style: String;
  dismissed: boolean = false;

  constructor(content, style?){
    this.content = content;
    this.style = style || 'info';
  }

}

@Injectable({
  providedIn: 'root'
})

export class ToastService {

  constructor() { }

}
