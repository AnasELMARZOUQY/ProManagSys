import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})

export class FilterPipe implements PipeTransform {
  transform(items: any[], searchText: string, filterFields: string[]): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLowerCase();

    return items.filter(item => {
      for (const field of filterFields) {
        if (item[field] && item[field].toLowerCase().includes(searchText)) {
          return true;
        }
      }
      return false;
    });
  }
}
