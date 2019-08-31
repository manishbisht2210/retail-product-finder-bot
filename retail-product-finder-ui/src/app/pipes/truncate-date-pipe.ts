import {Pipe, PipeTransform} from '@angular/core';
import * as moment from 'moment';

@Pipe({name: 'truncatedate'})
export class TruncateDatePipe implements PipeTransform {
  transform(date: string): string {
    return moment(date).format('MM/DD/YYYY');
  }
}
