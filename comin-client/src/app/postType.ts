import { FormArea } from './formArea';
import { Post } from './post';

export class PostType
 {
    id: number;
    name: string;
    usage: string;
    communityId: number;
    formAreas: Array<FormArea>;
    posts: Array<Post>;
}