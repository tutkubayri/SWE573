import { PostType } from './postType';

export class Post
{
    id: number;
    postText: string;
    semanticTag: string;
    selectedTags: string;
    postTypeId: number;
}