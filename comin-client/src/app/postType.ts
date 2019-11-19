import { FormArea } from './formArea';

export class PostType
 {
    id: number;
    name: string;
    usage: string;
    communityId: number;
    formAreas: Array<FormArea>;
}